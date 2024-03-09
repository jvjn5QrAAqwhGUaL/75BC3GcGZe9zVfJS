package moe.dic1911.esun_library.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import moe.dic1911.esun_library.component.CustomBasicAuth;
import moe.dic1911.esun_library.controller.ApiController;
import moe.dic1911.esun_library.data.UserDto;
import moe.dic1911.esun_library.repository.UserRepository;
import moe.dic1911.esun_library.service.UserDetailsServiceImpl;
import moe.dic1911.esun_library.util.SecurityUtils;
import moe.dic1911.esun_library.util.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CustomBasicAuth customBasicAuth;

    @Autowired
    public static InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryAuth;
    private static PasswordEncoder encoder;

    org.slf4j.Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((requests) -> {
                    try {
                        requests
                                .requestMatchers("/", "/login", "/home", "/ping", "/register", "/api/*").permitAll()
                                .anyRequest().authenticated()
                                .and().httpBasic().authenticationEntryPoint(customBasicAuth)
                                .and().formLogin().failureHandler((request, response, exception) -> {
                                    String user = request.getParameter("username");
                                    String pass = request.getParameter("password");
                                    UserDto userObj = userRepo.findByUsername(user);

                                    if (userObj == null && !inMemoryAuth.getUserDetailsService().userExists(user)) {
                                        logger.info("Adding user for " + user);
                                        pass = getPasswordEncoder().encode(pass);
                                        inMemoryAuth.getUserDetailsService().createUser(User.builder().username(user).password(pass).build());
//                                        inMemoryAuth.withUser(user).password(getPasswordEncoder().encode(pass));
                                        userRepo.addUser(user, pass);
                                    } else {
                                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                        response.getWriter().println("Wrong password?");
                                    }
                                    response.sendRedirect("/login_fail");
                                });
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // 030: auto salt
        encoder = new BCryptPasswordEncoder();
        inMemoryAuth = auth.inMemoryAuthentication();
        for (UserDto usr : userRepo.findAll()) {
            inMemoryAuth.withUser(usr.getUsername()).password(usr.getPassword()).roles("user");
        }
    }

    @Bean
    public static PasswordEncoder getPasswordEncoder() {
        return encoder;
    }

//    public static void updateAccount(WebUIAccount acc) {
////        if (inMemoryAuth.getUserDetailsService().userExists(acc.getCardNo()))
//        inMemoryAuth.getUserDetailsService().deleteUser(acc.getCardNo());
//
//        inMemoryAuth.withUser(acc.getCardNo()).password(acc.getPassword()).roles("user");
//
//    }
}
