package ru.ncedu.services;

import org.springframework.http.ResponseEntity;
import ru.ncedu.entity.Motor;

import java.util.List;

public interface MotorService {

    Motor findById(Long id);

    ResponseEntity<List<Motor>> findAllMotor();
}
