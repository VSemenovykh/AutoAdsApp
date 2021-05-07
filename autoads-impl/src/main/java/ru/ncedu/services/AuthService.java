package ru.ncedu.services;

import ru.ncedu.auth.request.LoginRequest;
import ru.ncedu.auth.request.SignupRequest;
import ru.ncedu.auth.response.MessageResponse;
import java.util.Map;

public interface AuthService {

    Map<String, Object> authenticateUser(LoginRequest loginRequest);

    MessageResponse registerUser(SignupRequest signUpRequest);
}
