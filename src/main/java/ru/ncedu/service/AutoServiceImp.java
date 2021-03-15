package ru.ncedu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.entity.Auto;
import ru.ncedu.repository.AutoRepository;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AutoServiceImp implements AutoService {

    private final AutoRepository autorepository;

    @Override
    public List<Auto> getAllAuto() {
        return autorepository.findAll();
    }

   @Override
    public List<Auto> findAll() {

        List<Auto> auto = new ArrayList<>();
        autorepository.findAll().forEach(auto::add);
        return auto;
    }

    @Override
    public Auto save(Auto auto) {

        return autorepository.save(auto);
    }

    @Override
    public Auto findById(Long id) {

        Auto auto = autorepository.findById(id).orElse(null);
        return auto;
    }

    @Transactional
    @Modifying
    @Override
    public void update(Auto auto) {

        autorepository.save(auto);
    }

    @Override
    public void delete(Long id) {

        autorepository.deleteById(id);
    }

    @Override
    public List<Auto> findByIdBrand(Long idBrand){
        return autorepository.findByIdBrand(idBrand);
    }

    @Override
    public List<Auto> findByIdMotor(Long idMotor){
        return autorepository.findByIdMotor(idMotor);
    }

    @Override
    public List<Auto> findByColor(String color) {
        return autorepository.findByColor(color);
    }

    @Override
    public List<Auto> findByPriceBetween(double startValue, double endValue){
        return autorepository.findByPriceBetween(startValue, endValue);
    }

    @Override
    public List<Auto> findByDrive(String drive){
        return  autorepository.findByDriveType(drive);
    }

    @Override
    public List<Auto> findByTransmission(String transmission){
        return autorepository.findByTransmissionType(transmission);
    }

    @Override
    public List<Auto> findByBodyStyle(String bodyStyle){
        return autorepository.findByBodyStyleType(bodyStyle);
    }


    /*search by different criteria */
    @Override
    public List<Auto> search(Long idBrand, Long idMotor, String color, double price, String drive, String transmission, String bodyStyle){
        return autorepository.search(idBrand, idMotor, color, price, drive, transmission, bodyStyle);
    }
}
