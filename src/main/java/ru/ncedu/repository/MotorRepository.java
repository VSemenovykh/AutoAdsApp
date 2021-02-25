package ru.ncedu.repository;	

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.Motor;
import ru.ncedu.entity.User;

import java.util.List;

@Repository	
public interface MotorRepository extends PagingAndSortingRepository<Motor, Long>, JpaSpecificationExecutor<Motor> {

    List<Motor> findAll();

    Motor findMotorById(Long idMotor);

    void deleteById(Long idMotor);

}