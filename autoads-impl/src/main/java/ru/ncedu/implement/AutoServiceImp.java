package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Contact;
import ru.ncedu.entity.Motor;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.repository.AutoRepository;
import ru.ncedu.service.*;

@Service
@RequiredArgsConstructor
public class AutoServiceImp implements AutoService {

    private final AutoRepository autorepository;

    private final BrandService brandService;

    private final MotorService motorService;

    private final PictureAutoService imageAutoService;

    private final ContactService contactService;

    @Override
    public Auto findById(Long id) {
        Auto auto = autorepository.findById(id).orElse(null);
        return new Auto( auto.getId(),
                         auto.getIdImage(),
                         auto.getIdBrand(),
                         auto.getIdContact(),
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
            Contact contact = contactService.findById(auto.getIdContact());

            byte[] raster = null;

            if(auto.getIdImage() != null){
                raster = imageAutoService.findPictureAutoById(auto.getIdImage()).getRaster();
            }

            return new AutoJoin( auto.getId(),
                                 auto.getIdImage(),
                                 raster,
                                 contact.getEmail(),
                                 contact.getPhone(),
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
}