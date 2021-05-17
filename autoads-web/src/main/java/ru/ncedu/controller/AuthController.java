package ru.ncedu.controller;

import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.auth.request.LoginRequest;
import ru.ncedu.auth.request.SignupRequest;
import ru.ncedu.auth.response.JwtResponse;
import ru.ncedu.services.AuthService;
import ru.ncedu.services.UserDetailsImpl;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Map<String, Object> outDataAuthenticateUser = authService.authenticateUser(loginRequest);
        String jwt = outDataAuthenticateUser.get("jwt").toString();
        UserDetailsImpl userDetails = (UserDetailsImpl) outDataAuthenticateUser.get("userDetails");
        List<String> roles = (List<String>) outDataAuthenticateUser.get("roles");

        return ResponseEntity.ok(new JwtResponse(jwt,
                                 userDetails.getId(),
                                 userDetails.getUsername(),
                                 userDetails.getEmail(),
                                 roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        return authService.registerUser(signUpRequest, getSiteURL(request));
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
