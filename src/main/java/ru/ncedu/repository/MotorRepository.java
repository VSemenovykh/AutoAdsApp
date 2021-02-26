package ru.ncedu.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.Motor;

import java.util.List;

@Repository
public interface MotorRepository extends PagingAndSortingRepository<Motor, Long> {

    List<Motor> findAll();

    Motor findMotorById(Long idMotor);

    void deleteById(Long idMotor);
}