package ru.ncedu.interfaces;

import ru.ncedu.entity.Motor;

import java.util.List;

public interface MotorRepositoryTest {

    Motor getMotorById(Long id);

    void saveMotor(Motor motor);

    List<Motor> findAll();

    void delete(Long id);
}
