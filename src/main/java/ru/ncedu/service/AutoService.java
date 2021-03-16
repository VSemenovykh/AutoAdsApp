package ru.ncedu.service;

import ru.ncedu.entity.Auto;

import java.util.List;

public interface AutoService {

    List<Auto> getAllAuto();

    Auto save(Auto auto);

    void update(Auto auto);

    void delete(Long id);

    List<Auto> findAll();

    Auto findById(Long id);

    List<Auto> findByIdBrand(Long idBrand);

    List<Auto> findByIdMotor(Long idMotor);

    List<Auto> findByColor(String color);

    List<Auto> findByPriceBetween(double startValue, double endValue);

    List<Auto> findByDrive(String drive);

    List<Auto> findByTransmission(String transmission);

    List<Auto> findByBodyStyle(String bodyStyle);

    /*search by different criteria */
    List<Auto> search(Long idBrand, Long idMotor, String color, double price, String drive, String transmission, String bodyStyle);
}
