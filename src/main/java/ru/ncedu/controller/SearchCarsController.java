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
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/search")
@Slf4j
public class SearchCarsController {

    private final AutoService autoService;

    private final BrandService brandService;

    private final MotorService motorService;

    /**search cars by different criteria.
     *Old method the searchAuto. He running
     **/
//    @PostMapping
//    public List<AutoJoin> searchAuto(@RequestBody AutoJoin car) {
//        List<AutoJoin> autoJoinList = new ArrayList<>();
//        List<Brand> brandList = new ArrayList<>();
//        List<Motor> motorList = new ArrayList<>();
//
//        Brand brand;
//        Motor motor;
//
//        Long idBrand = null;
//        Long idMotor = null;
//
//        if ((car.getNameBrand() != null) || (car.getNameModel() != null) || (car.getYear() != null)) {
//            brandList = brandService.searchBrand(car.getNameBrand(), car.getNameModel(), car.getYear());
//        }
//
//        if ((car.getMotorType() != null) || (car.getVolume() != 0.0)) {
//            motorList = motorService.searchByMotor(car.getMotorType(), car.getVolume());
//        }
//
//        int sizeBrandList = brandList.size();
//        int sizeMotorList = motorList.size();
//
//        int i = 0;
//        int j = 0;
//
//        do {
//            do {
//                if (sizeBrandList != 0) {
//                    idBrand = brandList.get(i).getId();
//                } else {
//                    sizeBrandList = 1;
//
//                }
//
//                if (sizeMotorList != 0) {
//                    idMotor = motorList.get(j).getId();
//
//                } else {
//                    sizeMotorList = 1;
//                }
//
//                List<Auto> newAutoList = autoService.search(idBrand, idMotor, car.getColor(), car.getPrice(), car.getDriveType(),
//                                                            car.getTransmissionType(), car.getBodyStyleType());
//
//                String nameBrand;
//                String nameModel;
//                String year;
//                String motorType;
//                double volume;
//
//                for (Auto objAuto : newAutoList) {
//                    brand = brandService.findById(objAuto.getIdBrand());
//                    motor = motorService.findById(objAuto.getIdMotor());
//
//                    nameBrand = brand.getNameBrand();
//                    nameModel = brand.getNameModel();
//                    year = brand.getYear();
//                    motorType = motor.getMotorType();
//                    volume = motor.getVolume();
//
//                    AutoJoin autoJoin = new AutoJoin(objAuto.getId(), nameBrand, nameModel, year, objAuto.getColor()
//                                                        ,objAuto.getPrice(), motorType, volume, objAuto.getDriveType()
//                                                    ,objAuto.getTransmissionType(), objAuto.getBodyStyleType());
//
//                    autoJoinList.add(autoJoin);
//                }
//
//                sizeMotorList--;
//                j++;
//            } while (sizeMotorList > 0);
//
//            sizeBrandList--;
//            i++;
//        } while (sizeBrandList > 0);
//
//        return autoJoinList;
//    }
    /**
     * New method the searchAuto
     * */
    @PostMapping
    public List<AutoJoin> searchAuto(@RequestBody AutoJoin car
                                     ,@RequestParam(value = "startYear", required = false) String startYear
                                     ,@RequestParam(value = "endYear", required = false) String endYear) {

        List<AutoJoin> newAutoList = autoService.searchAuto(
                                                            car.getNameBrand()
//                                                            ,car.getNameModel()
//                                                            ,startYear
//                                                            ,endYear
//                                                            ,car.getColor()
//                                                            ,car.getPrice()
//                                                            ,car.getMotorType()
                                                            ,car.getVolume()
//                                                            ,car.getDriveType()
//                                                            ,car.getTransmissionType()
                                                            ,car.getBodyStyleType()
                                                            );

        /*to check if the list is empty*/
        log.info("newAutoList: " + newAutoList);

        return newAutoList;
    }
}

