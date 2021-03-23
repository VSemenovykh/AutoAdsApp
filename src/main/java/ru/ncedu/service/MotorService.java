package ru.ncedu.service;

import ru.ncedu.entity.Motor;
import java.util.List;

public interface MotorService {

    Motor save(Motor motor);

    void update(Motor motor);

    void delete(Long idMotor);

    List<Motor> findAll();

    Motor findById(Long id);
}
