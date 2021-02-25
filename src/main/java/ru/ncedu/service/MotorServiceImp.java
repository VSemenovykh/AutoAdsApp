package ru.ncedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.entity.Motor;
import ru.ncedu.repository.MotorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MotorServiceImp implements MotorService{

    @Autowired
    MotorRepository motorRepository;

    @Override
    public List<Motor> getAllMotor(){
        return motorRepository.findAll();
    }

    @Override
    public List<Motor> findAll(int pageNumber, int rowPerPage) {
        List<Motor> contacts = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage,
                Sort.by("id").ascending());
        motorRepository.findAll(sortedByIdAsc).forEach(contacts::add);
        return contacts;
    }

    @Override
    public Motor save(Motor motor){
        return motorRepository.save(motor);
    }

    @Override
    public Motor findById(Long id){
        Motor motor = motorRepository.findById(id).orElse(null);
        return motor;
    }

    @Override
    public Motor findMotorByIdMotor(Long idMotor){
        Motor motor = motorRepository.findMotorById(idMotor);
        return motor;
    }

    @Override
    public void update(Motor motor){
        motorRepository.save(motor);
    }

    @Override
    public void delete(Long idMotor){
        motorRepository.deleteById(idMotor);
    }

}
