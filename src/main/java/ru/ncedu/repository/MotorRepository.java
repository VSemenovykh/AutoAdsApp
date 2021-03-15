package ru.ncedu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import ru.ncedu.entity.Motor;

@Repository
public interface MotorRepository extends JpaRepository<Motor, Long> {

    void deleteById(Long idMotor);

    List<Motor> findAll();

    Motor findMotorById(Long idMotor);

    List<Motor> findByMotorType(String motorType);

    List<Motor> findByVolumeBetween(double startValue, double endValue);

    /*search by different criteria */
    @Query(value = "SELECT m FROM Motor m where (:motorType is null or m.motorType = :motorType ) and (:volume is null or m.volume = :volume)", nativeQuery = false )
    List<Motor> search(@Param("motorType") String motorType, @Param("volume") double volume);
}
