package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.User;
import ru.ncedu.repositories.UserRepository;
import ru.ncedu.services.EmailService;
import ru.ncedu.services.VerifyUserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class VerifyUserController {

    private final VerifyUserService verifyUserService;
    private final EmailService emailService;
    private final UserRepository userRepository;

    private final String REDIRECT_LOGIN_UI = "http://localhost:4200/login/?verifyEnabled=";
    private final String REDIRECT_NOT_VERIFY_UI = "http://localhost:4200/not-verify/?email=";

    private User user;

    @GetMapping("/send-verify-user-email")
    public ResponseEntity sendVerifyUserEmail(@RequestParam("email") String email, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        user = userRepository.findByEmail(email);

        if (user != null) {
            sendVerificationEmail(user, getSiteURL(request));

            return new ResponseEntity<>("Email verification request has been sent!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error: user is not registered!", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<Object> verifyUser(@RequestParam("code") String code) throws URISyntaxException {
        if (verifyUserService.verify(code)) {
            user = userRepository.findByVerificationCode(code);
            URI redirect_login_ui = new URI(REDIRECT_LOGIN_UI + true + "&username="+user.getUsername());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(redirect_login_ui);

            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        } else {
            URI redirect_login_ui = new URI(REDIRECT_NOT_VERIFY_UI + user.getEmail());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(redirect_login_ui);

            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        }
    }

    public void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        emailService.sendMail(user, siteURL);
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
