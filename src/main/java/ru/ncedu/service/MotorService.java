package ru.ncedu.service;

import ru.ncedu.entity.Motor;
import java.util.List;

public interface MotorService {

    List<Motor> getAllMotor();

    Motor save(Motor motor);

    void update(Motor motor);

    void delete(Long idMotor);

    public List<Motor> findAll();

    Motor findById(Long id);

    List<Motor> findByMotor(String motorType);

    List<Motor> findByVolumeBetween(double startValue, double endValue);

    List<Motor> searchByMotor(String motorType, double volume);
}
