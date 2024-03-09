package moe.dic1911.esun_library.component;

import moe.dic1911.esun_library.config.WebSecurityConfig;
import moe.dic1911.esun_library.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class CustomBasicAuth extends BasicAuthenticationEntryPoint {
    @Autowired
    public static InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryAuth;

    @Autowired
    private UserRepository userRepo;

    org.slf4j.Logger logger = LoggerFactory.getLogger(CustomBasicAuth.class);

    @Override
    public void commence(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().println("HTTP Status 401 - " + authException.getMessage());
        logger.info("login failed for " + request.getRemoteUser());
//        if (!inMemoryAuth.getUserDetailsService().userExists(request.getRemoteUser())) {
//            String username = request.getRemoteUser();
//            String password = request.getPart()
//            userRepo.addUser(, )
//        }
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("Enter existing or desired credential");
        super.afterPropertiesSet();
    }
}
