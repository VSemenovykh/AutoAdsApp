package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Motor;
import ru.ncedu.repositories.MotorRepository;
import ru.ncedu.services.MotorService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MotorServiceImpl implements MotorService {

    private final MotorRepository motorRepository;

    @Override
    public Motor findById(Long id) {
        return motorRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<List<Motor>> findAllMotor() {
        List<Motor> motorList = motorRepository.findAll();
        return (!motorList.isEmpty()) ? new ResponseEntity<>(motorList, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
