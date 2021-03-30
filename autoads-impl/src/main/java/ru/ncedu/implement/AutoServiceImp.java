package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
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

        return new Auto( auto.getId(),
                         auto.getIdImage(),
                         auto.getIdBrand(),
                         auto.getIdMotor(),
                         auto.getColor(),
                         auto.getPrice(),
                         auto.getDriveType(),
                         auto.getTransmissionType(),
                         auto.getBodyStyleType() );
    }

    @Override
    public AutoJoin findAutoJoinById(Long id){
        Auto auto = autorepository.findById(id).orElse(null);

        if( auto != null){
            Brand brand = brandService.findById(auto.getIdBrand());
            Motor motor = motorService.findById(auto.getIdMotor());

            byte[] raster = null;

            if(auto.getIdImage() != null){
                raster = imageAutoService.findPictureAutoById(auto.getIdImage()).getRaster();
            }

            return new AutoJoin( auto.getId(),
                                 auto.getIdImage(),
                                 raster,
                                 brand.getNameBrand(),
                                 brand.getNameModel(),
                                 brand.getYear(),
                                 auto.getColor(),
                                 auto.getPrice(),
                                 motor.getMotorType(),
                                 motor.getVolume(),
                                 auto.getDriveType(),
                                 auto.getTransmissionType(),
                                 auto.getBodyStyleType() );
        }else {
            return  null;
        }
    }

    @Override
    public List<AutoJoin> searchAuto( String nameBrand,
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
                                      String bodyStyle ) {

        return autorepository.searchAuto( nameBrand,
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
