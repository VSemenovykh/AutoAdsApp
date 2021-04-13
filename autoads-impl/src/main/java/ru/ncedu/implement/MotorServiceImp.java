package  ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Motor;
import ru.ncedu.repository.MotorRepository;
import ru.ncedu.service.MotorService;

@Service
@RequiredArgsConstructor
public class MotorServiceImp implements MotorService {

    private final MotorRepository motorRepository;

    @Override
    public Motor findById(Long id) {
        return motorRepository.findById(id).orElse(null);
    }
}
