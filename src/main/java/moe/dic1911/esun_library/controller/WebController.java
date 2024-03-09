package moe.dic1911.esun_library.controller;

import moe.dic1911.esun_library.data.Book;
import moe.dic1911.esun_library.data.BorrowLog;
import moe.dic1911.esun_library.data.MyUserDetail;
import moe.dic1911.esun_library.data.UserDto;
import moe.dic1911.esun_library.repository.BookRepository;
import moe.dic1911.esun_library.util.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class WebController extends BaseController {

    @Autowired
    public static InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryAuth;

    org.slf4j.Logger logger = LoggerFactory.getLogger(WebController.class);

//    private static final HashMap<String, String> isbnToNameMap = new HashMap<>();
//    static {
//        for (Book book : bookRepo.findAll()) {
//            isbnToNameMap.put(book.getIsbn(), book.getName());
//        }
//    }

    @GetMapping({"/home", "/", "/index"})
    public String home(Model model) {
        addCommonAttribute(model);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            return "redirect:/dashboard";
        }

        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        addCommonAttribute(model);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        addCommonAttribute(model);
        return "register";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        addCommonAttribute(model);

        UserDto user = userRepo.findByUsername(auth.getName());
        model.addAttribute("name", user.getName());

        if (StringUtils.isNullOrEmpty(user.getName()) || StringUtils.isNullOrEmpty(user.getPhoneNum())) {
            logger.warn(String.format("profile not populated yet, name: %s, phone: %s", user.getName(), user.getPhoneNum()));
            return "redirect:/profile";
        }

        model.addAttribute("books", bookRepo.findAll());
        Iterable<BorrowLog> logs = borrowLogRepository.findAllByBorrowedByAndUnreturned(user.getUid());
        model.addAttribute("borrowed", logs);

        return "dashboard";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        addCommonAttribute(model);

        return "profile";
    }

    @GetMapping("/login_fail")
    public String loginFail(Model model) {
        return "login_fail";
    }

}
