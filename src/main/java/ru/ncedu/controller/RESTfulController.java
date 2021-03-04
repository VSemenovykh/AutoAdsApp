package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Motor;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.service.AutoService;
import ru.ncedu.service.BrandService;
import ru.ncedu.service.MotorService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping({"/auto-all"})
public class RESTfulController {

    private final AutoService autoService;

    private final BrandService brandService;

    private final MotorService motorService;

    @GetMapping
    public List<AutoJoin> getAllAuto() {

        List<Auto> auto = autoService.findAll();
        List<AutoJoin> listAutoJoin = new ArrayList<>();

        AutoJoin autoJoin;

        String brandName;
        String modelName;
        String year;

        String motorType;
        double volume;

        for (int i = 0; i < auto.size(); i++) {

            brandName = brandService.findById(auto.get(i).getIdBrand()).getNameBrand();
            modelName = brandService.findById(auto.get(i).getIdBrand()).getNameModel();
            year = brandService.findById(auto.get(i).getIdBrand()).getYear();

            motorType = motorService.findById(auto.get(i).getIdMotor()).getMotorType();
            volume = motorService.findById(auto.get(i).getIdMotor()).getVolume();

            autoJoin = new AutoJoin(auto.get(i).getId(), brandName, modelName, year, auto.get(i).getColor()
                                    ,auto.get(i).getPrice(), motorType, volume, auto.get(i).getDriveType()
                                    ,auto.get(i).getTransmissionType(), auto.get(i).getBodyStyleType());

            listAutoJoin.add(autoJoin);
        }

        return listAutoJoin;
    }

    @PostMapping(path = {"/add"})
    public AutoJoin createAuto(@RequestBody Auto auto) {

        auto.setDriveType(auto.getDriveType());
        auto.setTransmissionType(auto.getTransmissionType());
        auto.setBodyStyleType(auto.getBodyStyleType());
        Auto newAuto = autoService.save(auto);

        Brand brand = brandService.findById(auto.getIdBrand());
        Motor motor = motorService.findById(auto.getIdMotor());

        AutoJoin autoJoin = new AutoJoin(newAuto.getId(), brand.getNameBrand(), brand.getNameModel(), brand.getYear()
                                        ,newAuto.getColor(), newAuto.getPrice(), motor.getMotorType(), motor.getVolume()
                                        ,newAuto.getDriveType(), newAuto.getTransmissionType(), newAuto.getBodyStyleType());

        return autoJoin;
    }

    @GetMapping(path = {"/{id}"})
    public AutoJoin getAutoById(@PathVariable("id") long autoId) {

        Auto auto = autoService.findById(autoId);
        Brand brand = brandService.findById(auto.getIdBrand());
        Motor motor = motorService.findById(auto.getIdMotor());

        AutoJoin autoJoin = new AutoJoin(auto.getId(), brand.getNameBrand(), brand.getNameModel(), brand.getYear()
                                         ,auto.getColor(), auto.getPrice(), motor.getMotorType(), motor.getVolume()
                                         ,auto.getDriveType(), auto.getTransmissionType(), auto.getBodyStyleType());

        return autoJoin;
    }

    @PutMapping("/{id}")
    public AutoJoin updateAuto(@RequestBody Auto auto, @PathVariable("id") Long autoId) {

        auto.setId(autoId);
        auto.setIdBrand(auto.getIdBrand());
        auto.setIdMotor(auto.getIdMotor());
        auto.setColor(auto.getColor());
        auto.setPrice(auto.getPrice());
        auto.setDriveType(auto.getDriveType());
        auto.setTransmissionType(auto.getTransmissionType());
        auto.setBodyStyleType(auto.getBodyStyleType());
        autoService.update(auto);

        Brand brand = brandService.findById(auto.getIdBrand());
        Motor motor = motorService.findById(auto.getIdMotor());

        AutoJoin autoJoin = new AutoJoin(auto.getId(), brand.getNameBrand(), brand.getNameModel(), brand.getYear(),
                auto.getColor(), auto.getPrice(), motor.getMotorType(), motor.getVolume(),
                auto.getDriveType(), auto.getTransmissionType(), auto.getBodyStyleType());

        return autoJoin;
    }

    @DeleteMapping("/{id}") //value = "/auto-all/{autoId}/delete"
    public ResponseEntity deleteAuto(@PathVariable("id") Long autoId) {

        Auto auto = autoService.findById(autoId);
        auto.setIdBrand(0L);
        auto.setIdMotor(0L);

        autoService.delete(autoId);

        Brand brand = brandService.findById(auto.getIdBrand());
        Motor motor = motorService.findById(auto.getIdMotor());

        return ResponseEntity.ok().body("Auto with ID : " + autoId + " deleted with success!");
    }
}
