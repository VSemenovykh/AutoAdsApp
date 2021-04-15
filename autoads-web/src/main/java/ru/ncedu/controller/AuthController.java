package ru.ncedu.controller;

import java.util.*;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ncedu.payload.request.LoginRequest;
import ru.ncedu.payload.request.SignupRequest;
import ru.ncedu.payload.response.JwtResponse;
import ru.ncedu.service.AuthService;
import ru.ncedu.services.UserDetailsImpl;

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
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return ResponseEntity.ok(authService.registerUser(signUpRequest));
    }
}
