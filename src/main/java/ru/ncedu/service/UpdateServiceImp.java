package ru.ncedu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Motor;
import ru.ncedu.repository.AutoRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateServiceImp implements  UpdateService{

    private final AutoRepository autorepository;

    private final BrandService brandService;

    private final MotorService motorService;

    @Override
    public void updateAuto(Auto auto, Long autoId) {
        auto.setId(autoId);
        auto.setIdBrand(auto.getIdBrand());
        auto.setIdMotor(auto.getIdMotor());
        auto.setColor(auto.getColor());
        auto.setPrice(auto.getPrice());
        auto.setDriveType(auto.getDriveType());
        auto.setTransmissionType(auto.getTransmissionType());
        auto.setBodyStyleType(auto.getBodyStyleType());
        autorepository.save(auto);

        Brand brand = brandService.findById(auto.getIdBrand());
        Motor motor = motorService.findById(auto.getIdMotor());
    }
}
