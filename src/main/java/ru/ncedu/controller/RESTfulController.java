package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
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
@CrossOrigin(origins = {"http://localhost:8080"})
public class RESTfulController {

    private final AutoService autoService;

    private final BrandService brandService;

    private final MotorService motorService;

    @GetMapping("/auto-all")
    public ResponseEntity<List<AutoJoin>> getAllAuto() {

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

        return new ResponseEntity<List<AutoJoin>>(listAutoJoin, HttpStatus.OK);
    }

    @PostMapping("/auto-all/add")
    public ResponseEntity<Void> createAuto(@RequestBody Auto auto, UriComponentsBuilder builder) {

        auto.setDriveType(auto.getDriveType());
        auto.setTransmissionType(auto.getTransmissionType());
        auto.setBodyStyleType(auto.getBodyStyleType());
        Auto newAuto = autoService.save(auto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/add?id={id}").buildAndExpand(autoService.save(auto)).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/auto-all/{autoId}")
    public ResponseEntity<AutoJoin> getAutoById(@PathVariable long autoId) {

        Auto auto = autoService.findById(autoId);
        Brand brand = brandService.findById(auto.getIdBrand());
        Motor motor = motorService.findById(auto.getIdMotor());

        AutoJoin autoJoin = new AutoJoin(auto.getId(), brand.getNameBrand(), brand.getNameModel(), brand.getYear(),
                auto.getColor(), auto.getPrice(), motor.getMotorType(), motor.getVolume(),
                auto.getDriveType(), auto.getTransmissionType(), auto.getBodyStyleType());

        return new ResponseEntity<AutoJoin>(autoJoin, HttpStatus.OK);
    }

    @PutMapping("/auto-all/{autoId}/edit")
    public ResponseEntity<Auto> updateAuto(@RequestBody Auto auto, @PathVariable Long autoId) {

        auto.setId(autoId);
        auto.setIdBrand(auto.getIdBrand());
        auto.setIdMotor(auto.getIdMotor());
        auto.setColor(auto.getColor());
        auto.setPrice(auto.getPrice());
        auto.setDriveType(auto.getDriveType());
        auto.setTransmissionType(auto.getTransmissionType());
        auto.setBodyStyleType(auto.getBodyStyleType());
        autoService.update(auto);

        return new ResponseEntity<Auto>(auto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/auto-all/{autoId}/delete")
    public ResponseEntity deleteAuto(@PathVariable long autoId) {

        Auto auto = autoService.findById(autoId);
        auto.setIdBrand(0L);
        auto.setIdMotor(0L);

        autoService.delete(autoId);

        return ResponseEntity.ok().body("Auto with ID : " + autoId + " deleted with success!");
    }
}