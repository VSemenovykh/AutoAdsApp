package ru.ncedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ncedu.entity.Motor;
import ru.ncedu.exception.BadResourceException;
import ru.ncedu.exception.ResourceNotFoundException;
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

    private boolean existsById(Long id) {
        return motorRepository.existsById(id);
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
    public Motor findById(Long id) throws ResourceNotFoundException {

        Motor motor = motorRepository.findById(id).orElse(null);

        if (motor==null) {

            throw new ResourceNotFoundException("Cannot find motor with id: " + id);

        }else{

            return motor;
        }
    }

    @Override
    public Motor findMotorByIdMotor(Long idAuto){
        Motor motor = motorRepository.findMotorById(idAuto);
        return motor;
    }

    @Override
    public void update(Motor motor) throws BadResourceException, ResourceNotFoundException {

        if (!StringUtils.isEmpty(motor.getMotorType())) {
            if (!existsById(motor.getId())) {

                throw new ResourceNotFoundException("Cannot find motor with id: " + motor.getId());
            }
            motorRepository.save(motor);

        }else{
            BadResourceException exc = new BadResourceException("Failed to save motor");
            exc.addErrorMessage("Motor is null or empty");
            throw exc;
        }
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {

        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find motor with id: " + id);
        }
        else {
            motorRepository.deleteById(id);
        }
    }
}
