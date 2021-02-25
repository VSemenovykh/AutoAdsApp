package ru.ncedu.service;

import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Motor;

import java.util.List;

public interface MotorService {

    List<Motor> getAllMotor();

    List<Motor> findAll(int pageNumber, int rowPerPage);

    Motor save(Motor motor);

    Motor findById(Long id);

    void update(Motor motor);

    Motor findMotorByIdMotor(Long idAuto);

    void delete(Long id);
}
