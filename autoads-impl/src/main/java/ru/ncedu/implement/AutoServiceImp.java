package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Motor;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.repository.AutoRepository;
import ru.ncedu.service.AutoService;
import ru.ncedu.service.BrandService;
import ru.ncedu.service.PictureAutoService;
import ru.ncedu.service.MotorService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AutoServiceImp implements AutoService {

    private final AutoRepository autorepository;

    private final BrandService brandService;

    private final MotorService motorService;

    private final PictureAutoService imageAutoService;

    @Override
    public Auto findById(Long id) {
        Auto auto = autorepository.findById(id).orElse(null);

        return new Auto( auto.getId()
                            , auto.getIdImage()
                            , auto.getIdBrand()
                            , auto.getIdMotor()
                            ,auto.getColor()
                            ,auto.getPrice()
                            ,auto.getDriveType()
                            ,auto.getTransmissionType()
                            ,auto.getBodyStyleType());
    }

    @Override
    public AutoJoin findAutoJoinById(Long id){
        Optional<Auto> auto = Optional.ofNullable(autorepository.findById(id).orElse(null));
        Brand brand = brandService.findById(auto.get().getIdBrand());
        Motor motor = motorService.findById(auto.get().getIdMotor());

        byte[] raster = null;

        if(auto.get().getIdImage() != null){
            raster = imageAutoService.findPictureAutoById(auto.get().getIdImage()).getRaster();
        }

        return new AutoJoin( auto.get().getId()
                ,auto.get().getIdImage()
                ,raster
                ,brand.getNameBrand()
                ,brand.getNameModel()
                ,brand.getYear()
                ,auto.get().getColor()
                ,auto.get().getPrice()
                ,motor.getMotorType()
                ,motor.getVolume()
                ,auto.get().getDriveType()
                ,auto.get().getTransmissionType()
                ,auto.get().getBodyStyleType());
    }

    /*search by different criteria */
    @Override
    public List<AutoJoin> searchAuto(
                                     String nameBrand,
                                     String nameModel,
                                     String startYear,
                                     String endYear,
                                     String color,
                                     Double startPrice,
                                     Double endPrice,
                                     String motorType,
                                     Double startVolume,
                                     Double endVolume,
                                     String drive,
                                     String transmission,
                                     String bodyStyle
                                    ) {
        return autorepository.searchAuto(
                                        nameBrand,
                                        nameModel,
                                        startYear,
                                        endYear,
                                        color,
                                        startPrice,
                                        endPrice,
                                        motorType,
                                        startVolume,
                                        endVolume,
                                        drive,
                                        transmission,
                                        bodyStyle);
    }
}
