package ru.ncedu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Motor;
import ru.ncedu.repository.MotorRepository;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MotorServiceImp implements MotorService {

    private final MotorRepository motorRepository;

    @Override
    public List<Motor> getAllMotor() {
        return motorRepository.findAll();
    }

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

    @Override
    public List<Motor> findByMotor(String motorType) {
        return motorRepository.findByMotorType(motorType);
    }

    @Override
    public List<Motor> findByVolumeBetween(double startValue, double endValue){
        return motorRepository.findByVolumeBetween(startValue, endValue);
    }

    @Override
    public List<Motor> searchByMotor(String motorType, double volume){
        return motorRepository.search(motorType, volume);
    }
}
