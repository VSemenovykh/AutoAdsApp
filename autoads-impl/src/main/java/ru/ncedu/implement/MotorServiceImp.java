package  ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Motor;
import ru.ncedu.repository.MotorRepository;
import ru.ncedu.service.MotorService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MotorServiceImp implements MotorService {

    private final MotorRepository motorRepository;

    @Override
    public Motor save(Motor motor) {
        return motorRepository.save(motor);
    }

    @Override
    public void update(Motor motor) {
        motorRepository.save(motor);

    }

    @Override
    public void delete(Long idMotor) {
        motorRepository.deleteById(idMotor);

    }

    @Override
    public List<Motor> findAll(){
        List<Motor> motor = new ArrayList<>();
        motorRepository.findAll().forEach(motor::add);
        return motor;
    }

    @Override
    public Motor findById(Long id) {
        Motor motor = motorRepository.findById(id).orElse(null);
        return motor;
    }
}
