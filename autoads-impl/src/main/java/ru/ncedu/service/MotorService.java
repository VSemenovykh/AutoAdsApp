package ru.ncedu.service;

import ru.ncedu.entity.Motor;
import java.util.List;

public interface MotorService {
    List<Motor> findAll();

    Motor findById(Long id);

    void delete(Long idMotor);

    Motor save(Motor motor);

    void update(Motor motor);
}
