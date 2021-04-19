package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Role;
import ru.ncedu.entity.User;
import ru.ncedu.jwt.JwtUtils;
import ru.ncedu.model.ERole;
import ru.ncedu.payload.request.LoginRequest;
import ru.ncedu.payload.request.SignupRequest;
import ru.ncedu.payload.response.MessageResponse;
import ru.ncedu.repository.RoleRepository;
import ru.ncedu.repository.UserRepository;
import ru.ncedu.service.AuthService;
import ru.ncedu.services.UserDetailsImpl;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    @Override
    public Map<String, Object> authenticateUser(@Valid LoginRequest loginRequest) {
        log.info("AuthServiceImp -> authenticateUser()");
        log.info("AuthServiceImp -> LoginRequest -> isNull: " + isNull(loginRequest));
        Map<String, Object> outDataAuthenticateUser = new HashMap();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        log.info("AuthServiceImp -> Authentication -> isNull: " + isNull(authentication));
        log.info("AuthServiceImp -> authentication.toString: " + authentication.toString());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        log.info("AuthServiceImp -> jwt: " + jwt);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        log.info("AuthServiceImp -> UserDetailsImpl -> isNull : " + isNull(userDetails));
        log.info("AuthServiceImp -> userDetails.toString : " + userDetails.toString());

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        log.info("AuthServiceImp -> List<String> -> isEmpty : " + roles.isEmpty());

        outDataAuthenticateUser.put("jwt", jwt);
        outDataAuthenticateUser.put("roles", roles);
        outDataAuthenticateUser.put("userDetails", userDetails);

        return outDataAuthenticateUser;
    }

    @Override
    public MessageResponse registerUser(SignupRequest signUpRequest) {
        log.info("AuthServiceImp -> registerUser()");
        log.info("AuthServiceImp -> SignupRequest -> isNull: " + isNull(signUpRequest));
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new MessageResponse("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new MessageResponse("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                             signUpRequest.getEmail(),
                             encoder.encode(signUpRequest.getPassword()));
        log.info("AuthServiceImp -> user.toString: " + user.toString());

        Set<String> strRoles = signUpRequest.getRole();
        log.info("AuthServiceImp -> Set<String> -> isEmpty: " + strRoles.isEmpty());
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
        userRepository.save(user);

        return new MessageResponse("User registered successfully!");
    }

}
