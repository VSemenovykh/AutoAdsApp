package ru.ncedu.services;

import org.springframework.http.ResponseEntity;
import ru.ncedu.auth.request.LoginRequest;
import ru.ncedu.auth.request.SignupRequest;
import ru.ncedu.auth.response.MessageResponse;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface AuthService {

    Map<String, Object> authenticateUser(LoginRequest loginRequest);

    ResponseEntity<Map<String, Object>> registerUser(SignupRequest signUpRequest, String siteURL) throws UnsupportedEncodingException, MessagingException;
}
