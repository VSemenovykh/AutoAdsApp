package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Motor;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.repository.AutoRepository;
import ru.ncedu.service.BrandService;
import ru.ncedu.service.CreateService;
import ru.ncedu.service.MotorService;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateServiceImp implements CreateService {

    private final AutoRepository autorepository;

    private final BrandService brandService;

    private final MotorService motorService;

    @Override
    public AutoJoin saveAuto(Auto auto) {

        Auto newAuto = autorepository.save(auto);
        Brand brand = brandService.findById(auto.getIdBrand());
        Motor motor = motorService.findById(auto.getIdMotor());

        AutoJoin autoJoin = new AutoJoin( newAuto.getId()
                                         ,brand.getNameBrand()
                                         ,brand.getNameModel()
                                         ,brand.getYear()
                                         ,newAuto.getColor()
                                         ,newAuto.getPrice()
                                         ,motor.getMotorType()
                                         ,motor.getVolume()
                                         ,newAuto.getDriveType()
                                         ,newAuto.getTransmissionType()
                                         ,newAuto.getBodyStyleType());

        return autoJoin;
    }
}
