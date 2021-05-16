package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ncedu.auth.request.LoginRequest;
import ru.ncedu.auth.request.SignupRequest;
import ru.ncedu.entity.Role;
import ru.ncedu.entity.User;
import ru.ncedu.jwt.JwtUtils;
import ru.ncedu.model.ERole;
import ru.ncedu.repositories.RoleRepository;
import ru.ncedu.repositories.UserRepository;
import ru.ncedu.services.AuthService;
import ru.ncedu.services.EmailService;
import ru.ncedu.services.UserDetailsImpl;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final EmailService emailService;

    @Override
    public Map<String, Object> authenticateUser(@Valid LoginRequest loginRequest) {
        Map<String, Object> outDataAuthenticateUser = new HashMap();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        outDataAuthenticateUser.put("jwt", jwt);
        outDataAuthenticateUser.put("roles", roles);
        outDataAuthenticateUser.put("userDetails", userDetails);

        return outDataAuthenticateUser;
    }

    @Override
    public ResponseEntity<Map<String, Object>> registerUser(SignupRequest signUpRequest, String siteURL) throws UnsupportedEncodingException, MessagingException {
        Map<String, Object> outRegisterUser = new HashMap<>();
        outRegisterUser.put("existUser", true);
        outRegisterUser.put("message", "Error: username is already taken/ email is already in use");

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            outRegisterUser.remove("existUser");
            outRegisterUser.put("existUser", false);
            return new ResponseEntity<>(outRegisterUser, HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            outRegisterUser.remove("existUser");
            outRegisterUser.put("existUser", false);
            return new ResponseEntity<>(outRegisterUser, HttpStatus.BAD_REQUEST);
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                            signUpRequest.getEmail(),
                            encoder.encode(signUpRequest.getPassword()),
                            signUpRequest.getVerificationCode(),
                            signUpRequest.getVerifyEnabled());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);

        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);

        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setVerifyEnabled(false);
        userRepository.save(user);

        outRegisterUser.remove("message", "User registered successfully!");
        return new ResponseEntity<>(outRegisterUser, HttpStatus.OK);
    }
}
