package ru.ncedu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.Auto;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AutoRepository extends JpaRepository<Auto, Long> {

    List<Auto> findAll();

    void deleteById(Long id);

    List<Auto> findByIdBrand(Long idBrand);

    List<Auto> findByIdMotor(Long idMotor);

    List<Auto> findByColor(String color);

    List<Auto> findByPriceBetween(double startValue, double endValue);

    List<Auto> findByDriveType(String drive);

    List<Auto> findByTransmissionType(String transmission);

    List<Auto> findByBodyStyleType(String bodyStyle);

    /*search by different criteria */
    @Query(value = "SELECT a FROM Auto a where (:idBrand is null or a.idBrand = :idBrand ) and (:idMotor is null or a.idMotor = :idMotor ) and (:color is null or a.color = :color ) and" +
                   " (:price is null or a.price >= :price) and (:driveType is null or a.driveType = :driveType) and (:transmissionType is null or a.transmissionType = :transmissionType) " +
                    "and (: bodyStyleType is null or a.bodyStyleType = :bodyStyleType)", nativeQuery = false )
    List<Auto> search(@Param("idBrand") Long idBrand, @Param("idMotor") Long idMotor, @Param("color") String color,  @Param("price") double price,
                      @Param("driveType") String driveType, @Param("transmissionType") String transmissionType,  @Param("bodyStyleType") String bodyStyleType);

}
