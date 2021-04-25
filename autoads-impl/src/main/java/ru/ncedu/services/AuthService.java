package ru.ncedu.services;

import ru.ncedu.payload.request.LoginRequest;
import ru.ncedu.payload.request.SignupRequest;
import ru.ncedu.payload.response.MessageResponse;
import java.util.Map;

public interface AuthService {

    Map<String, Object> authenticateUser(LoginRequest loginRequest);

    MessageResponse registerUser(SignupRequest signUpRequest);
}
