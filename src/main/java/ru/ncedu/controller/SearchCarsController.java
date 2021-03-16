package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping({"/api/search"})
@Slf4j
public class SearchCarsController {

    private final AutoService autoService;

    private final BrandService brandService;

    private final MotorService motorService;

    /*Search cars by color*/
    @GetMapping(path = "/findColor")
    public List<AutoJoin> getAutoByColor(@RequestParam("color") String color) {

        List<Auto> autoList = autoService.findByColor(color);
        List<AutoJoin> autoJoinList = new ArrayList<>();

        AutoJoin autoJoin;
        Brand brand;
        Motor motor;

        String nameBrand;
        String nameModel;
        String year;

        String motorType;
        double volume;

        for(Auto auto: autoList){

            brand = brandService.findById(auto.getIdBrand());
            motor = motorService.findById(auto.getIdMotor());

            nameBrand = brand.getNameBrand();
            nameModel = brand.getNameModel();
            year = brand.getYear();

            motorType = motor.getMotorType();
            volume = motor.getVolume();

            autoJoin = new AutoJoin(auto.getId(), nameBrand, nameModel, year, color
                    ,auto.getPrice(), motorType, volume, auto.getDriveType()
                    ,auto.getTransmissionType(), auto.getBodyStyleType());

            autoJoinList.add(autoJoin);
        }

        return autoJoinList;
    }

    /*Search cars by price*/
    @GetMapping(path = "/findPrice")
    public List<AutoJoin> getAutoByPrice(@RequestParam("startValue") double startValue,
                                         @RequestParam("endValue") double endValue) {

        List<Auto> autoList = autoService.findByPriceBetween(startValue, endValue);
        List<AutoJoin> autoJoinList = new ArrayList<>();

        AutoJoin autoJoin;
        Brand brand;
        Motor motor;

        String nameBrand;
        String nameModel;
        String year;

        String motorType;
        double volume;

        for(Auto auto: autoList){

            brand = brandService.findById(auto.getIdBrand());
            motor = motorService.findById(auto.getIdMotor());

            nameBrand = brand.getNameBrand();
            nameModel = brand.getNameModel();
            year = brand.getYear();

            motorType = motor.getMotorType();
            volume = motor.getVolume();

            autoJoin = new AutoJoin(auto.getId(), nameBrand, nameModel, year, auto.getColor()
                    ,auto.getPrice(), motorType, volume, auto.getDriveType()
                    ,auto.getTransmissionType(), auto.getBodyStyleType());

            autoJoinList.add(autoJoin);
        }

        return autoJoinList;
    }

    /*Search cars by drive type*/
    @GetMapping(path = "/findDrive")
    public List<AutoJoin> getAutoByDrive(@RequestParam(name = "driveType")  String driveType) {

        List<Auto> autoList = autoService.findByDrive(driveType);
        List<AutoJoin> autoJoinList = new ArrayList<>();

        AutoJoin autoJoin;
        Brand brand;
        Motor motor;

        String nameBrand;
        String nameModel;
        String year;

        String motorType;
        double volume;

        for(Auto auto: autoList){

            brand = brandService.findById(auto.getIdBrand());
            motor = motorService.findById(auto.getIdMotor());

            nameBrand = brand.getNameBrand();
            nameModel = brand.getNameModel();
            year = brand.getYear();

            motorType = motor.getMotorType();
            volume = motor.getVolume();

            autoJoin = new AutoJoin(auto.getId(), nameBrand, nameModel, year, auto.getColor()
                                   ,auto.getPrice(), motorType, volume, driveType, auto.getTransmissionType()
                                   ,auto.getBodyStyleType());

            autoJoinList.add(autoJoin);
        }

        return autoJoinList;
    }

    /*Search cars by transmission type*/
    @GetMapping(path = "/findTransmission")
    public List<AutoJoin> getAutoByTransmission(@RequestParam(name = "transmissionType") String transmissionType) {

        List<Auto> autoList = autoService.findByTransmission(transmissionType);
        List<AutoJoin> autoJoinList = new ArrayList<>();

        AutoJoin autoJoin;
        Brand brand;
        Motor motor;

        String nameBrand;
        String nameModel;
        String year;

        String motorType;
        double volume;

       for(Auto auto: autoList){

            brand = brandService.findById(auto.getIdBrand());
            motor = motorService.findById(auto.getIdMotor());

            nameBrand = brand.getNameBrand();
            nameModel = brand.getNameModel();
            year = brand.getYear();

            motorType = motor.getMotorType();
            volume = motor.getVolume();

            autoJoin = new AutoJoin(auto.getId(), nameBrand, nameModel, year, auto.getColor()
                                    ,auto.getPrice(), motorType, volume, auto.getDriveType(),transmissionType
                                    ,auto.getBodyStyleType());

            autoJoinList.add(autoJoin);
        }

        return autoJoinList;
    }

    /*Search cars by body style type*/
    @GetMapping(path = "/findBodyStyle")
    public List<AutoJoin> getAutoByBodyStyle(@RequestParam("bodyStyleType") String bodyStyleType) {

        List<Auto> autoList = autoService.findByBodyStyle(bodyStyleType);
        List<AutoJoin> autoJoinList = new ArrayList<>();

        AutoJoin autoJoin;

        String brandName;
        String modelName;
        String year;

        String motorType;
        double volume;

        for(Auto auto: autoList){

            brandName = brandService.findById(auto.getIdBrand()).getNameBrand();
            modelName = brandService.findById(auto.getIdBrand()).getNameModel();
            year = brandService.findById(auto.getIdBrand()).getYear();

            motorType = motorService.findById(auto.getIdMotor()).getMotorType();
            volume = motorService.findById(auto.getIdMotor()).getVolume();

            autoJoin = new AutoJoin(auto.getId(), brandName, modelName, year, auto.getColor()
                                   ,auto.getPrice(), motorType, volume, auto.getDriveType()
                                   ,auto.getTransmissionType(), bodyStyleType);

            autoJoinList.add(autoJoin);
        }

        return autoJoinList;
    }

    /*search cars by different criteria */
    @PostMapping
    public List<AutoJoin> searchCars(@RequestBody AutoJoin car) {
        List<AutoJoin> autoJoinList = new ArrayList<>();
        List<Brand> brandList = new ArrayList<>();
        List<Motor> motorList = new ArrayList<>();

        Brand brand;
        Motor motor;

        Long idBrand = null;
        Long idMotor = null;

        if ((car.getNameBrand() != null) || (car.getNameModel() != null) || (car.getYear() != null)) {
            brandList = brandService.searchBrand(car.getNameBrand(), car.getNameModel(), car.getYear());
        }

        if ((car.getMotorType() != null) || (car.getVolume() != 0.0)) {
            motorList = motorService.searchByMotor(car.getMotorType(), car.getVolume());
        }

        int sizeBrandList = brandList.size();
        int sizeMotorList = motorList.size();

        int i = 0;
        int j = 0;

        do {
            do {
                if (sizeBrandList != 0) {
                    idBrand = brandList.get(i).getId();
                } else {
                    sizeBrandList = 1;
                }

                if (sizeMotorList != 0) {
                    idMotor = motorList.get(j).getId();

                } else {
                    sizeMotorList = 1;
                }

                List<Auto> newAutoList = autoService.search(idBrand, idMotor, car.getColor(), car.getPrice(), car.getDriveType(),
                                                            car.getTransmissionType(), car.getBodyStyleType());

                String nameBrand;
                String nameModel;
                String year;
                String motorType;
                double volume;

                for (Auto objAuto : newAutoList) {
                    brand = brandService.findById(objAuto.getIdBrand());
                    motor = motorService.findById(objAuto.getIdMotor());

                    nameBrand = brand.getNameBrand();
                    nameModel = brand.getNameModel();
                    year = brand.getYear();
                    motorType = motor.getMotorType();
                    volume = motor.getVolume();

                    AutoJoin autoJoin = new AutoJoin(objAuto.getId(), nameBrand, nameModel, year, objAuto.getColor()
                                                        ,objAuto.getPrice(), motorType, volume, objAuto.getDriveType()
                                                    ,objAuto.getTransmissionType(), objAuto.getBodyStyleType());

                    autoJoinList.add(autoJoin);
                }

                sizeMotorList--;
                j++;
            } while (sizeMotorList > 0);

            sizeBrandList--;
            i++;
        } while (sizeBrandList > 0);

        return autoJoinList;
    }

    @PostMapping("/brand")
    public List<Brand> searchByBrand(@RequestBody Brand brand) {
        List<Brand> brandList = brandService.searchBrand(brand.getNameBrand(), brand.getNameModel(), brand.getYear());
        return brandList;
    }

    @PostMapping("/motor")
    public List<Motor> searchMotor(@RequestBody Motor motor) {
        List<Motor> motorList = motorService.searchByMotor(motor.getMotorType(), motor.getVolume());
        return motorList;
    }
}

