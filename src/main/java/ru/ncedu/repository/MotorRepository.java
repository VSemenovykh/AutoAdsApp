package ru.ncedu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.Motor;

@Repository
public interface MotorRepository extends JpaRepository<Motor,Long>{

}
