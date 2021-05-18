package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.User;
import ru.ncedu.repositories.UserRepository;
import ru.ncedu.services.VerifyUserService;

@Service
@RequiredArgsConstructor
public class VerifyUserServiceImpl implements VerifyUserService {

    private final UserRepository userRepository;

    @Override
    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.getVerified()) {
            return false;
        } else {
            user.setId(user.getId());
            user.setVerified(true);
            userRepository.save(user);

            return true;
        }
    }
}
