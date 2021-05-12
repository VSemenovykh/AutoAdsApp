package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Motor;
import ru.ncedu.repositories.MotorRepository;
import ru.ncedu.services.MotorService;

@Slf4j
@Service
@RequiredArgsConstructor
public class MotorServiceImpl implements MotorService {

    private final MotorRepository motorRepository;

    @Override
    public Motor findById(Long id) {
        return motorRepository.findById(id).orElse(null);
    }
}
