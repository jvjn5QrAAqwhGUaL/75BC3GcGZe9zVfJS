package moe.dic1911.esun_library.controller;

import jakarta.servlet.http.HttpServletRequest;
import moe.dic1911.esun_library.data.*;
import moe.dic1911.esun_library.repository.InventoryRepository;
import moe.dic1911.esun_library.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.Semaphore;

@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {
    @Autowired
    UserRepository userRepo;

    @Autowired
    AuthenticationManager authManager;

    org.slf4j.Logger logger = LoggerFactory.getLogger(ApiController.class);

//    @PostMapping("/register")
//    public BaseApiResponse register(@RequestBody UserDto user, BindingResult result, Model model) throws NoSuchAlgorithmException {
//        addCommonAttribute(model);
//        BaseApiResponse resp = new BaseApiResponse();
//        logger.info(user.toString());
//        int status = result.hasErrors() ? 1 : 0;
//
//        // validates manually
//        if (!user.getPassword().equals(user.getMatchingPassword())) {
//            status = 1;
//        }
//
//        if (status == 0 && user.getPassword().length() < 8) {
//            status = 2;
//        }
//
//        user.setName(HtmlUtils.htmlEscape(user.getName()));
//
//        resp.setStatus(status);
//
//        if (status == 0) {
//            userRepo.addUser(user.getName(), user.getPassword(), user.getPhoneNum(), user.getUsername());
//        }
//        for (UserDto userDto : userRepo.findAll()) {
//            logger.info(userDto.toString());
//        }
//        return resp;
//    }

//    @PostMapping("/login")
//    public BaseApiResponse login(@RequestBody UserDto user, BindingResult result, Model model) {
//        BaseApiResponse resp = new BaseApiResponse();
//        logger.info("Received login attempt of user " + user.getUsername());
//
//        UserDto targetUser = userRepo.findByUsername(user.getUsername());
//        if (targetUser == null) {
//            resp.setStatus(1);
//        }
//
//        String hashed = null;
//        try {
//            hashed = SecurityUtils.getHashedPassword(user.getPassword(), targetUser.getSalt());
//            if (!hashed.equals(targetUser.getPassword())) {
//                resp.setStatus(1);
//                return resp;
//            }
//        } catch (NoSuchAlgorithmException ex) {
//            resp.setStatus(-1);
//            return resp;
//        } catch (NullPointerException ex) {
//            resp.setStatus(-2);
//            return resp;
//        }
//
//        UsernamePasswordAuthenticationToken authReq
//                = new UsernamePasswordAuthenticationToken(user.getUsername(), hashed);
//        Authentication auth = authManager.authenticate(authReq);
//        SecurityContext sc = SecurityContextHolder.getContext();
//        sc.setAuthentication(auth);
//        return resp;
//    }

    @PutMapping("/updateProfile")
    public BaseApiResponse updateProfile(@RequestBody UserDto user, HttpServletRequest request) {
        BaseApiResponse resp = new BaseApiResponse();
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null || !auth.isAuthenticated()) throw new Exception("not authenticated");
            if (userRepo.findByPhoneNum(user.getPhoneNum()) != null) throw new Exception("dup phone use");

            UserDto target = userRepo.findByUsername(auth.getName());

            // 030: NO XSS
            target.setName(HtmlUtils.htmlEscape(user.getName()));
            target.setPhoneNum(HtmlUtils.htmlEscape(user.getPhoneNum()));

            userRepo.save(target);
        } catch (Exception ex) {
            resp.setStatus(-1);
            resp.setMessage(ex.getMessage());
            logger.error("error while updating profile detail", ex);
        }
        return resp;
    }

    private static final Semaphore bookMutex = new Semaphore(1);
    @PutMapping("/borrow")
    public BaseApiResponse borrow(@RequestBody Book book) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = userRepo.findByUsername(auth.getName());
        BaseApiResponse resp = new BaseApiResponse();
        try {
            bookMutex.acquire();
            Book target = bookRepo.getBookByIsbn(book.getIsbn());
            Inventory targetEntry = inventoryRepo.findFirstAvailableByIsbn(book.getIsbn());
            if (targetEntry == null) {
                resp.setStatus(1);
                resp.setMessage("not available, all out");
            } else {
                target.setAvailableCount(target.getAvailableCount() - 1);
                targetEntry.setStatus(1);

                borrowLogRepository.addLog(targetEntry.getInventoryId(), user.getUid());
                bookRepo.save(target);
                inventoryRepo.save(targetEntry);
            }
        } catch (InterruptedException e) {
            resp.setStatus(-1);
            resp.setMessage("lock failure");
        } finally {
            bookMutex.release();
        }
        return resp;
    }
    @PutMapping("/return")
    public BaseApiResponse returnBook(@RequestBody Inventory book) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = userRepo.findByUsername(auth.getName());
        BaseApiResponse resp = new BaseApiResponse();
        try {
            bookMutex.acquire();


            Inventory targetEntry = inventoryRepo.findFirstByInventoryId(book.getInventoryId());
            Book target = bookRepo.getBookByIsbn(targetEntry.getIsbn());
            logger.info(String.format("%s is returning a book, isbn: %s, id: %d", user.getName(), target.getIsbn(), book.getInventoryId()));
            target.setAvailableCount(target.getAvailableCount() + 1);
            targetEntry.setStatus(0); // magic?

            Timestamp ts = Timestamp.from(Instant.now());
            for (BorrowLog log : borrowLogRepository.findAllByBorrowedByAndUnreturned(user.getUid())) {
                if (Objects.equals(log.getInventoryId(), targetEntry.getInventoryId())) {
                    log.setReturnedAt(ts);
                    borrowLogRepository.save(log);
                }
            }

            bookRepo.save(target);
            inventoryRepo.save(targetEntry);
        } catch (InterruptedException e) {
            resp.setStatus(-1);
            resp.setMessage("lock failure");
        } finally {
            bookMutex.release();
        }
        return resp;
    }
}
