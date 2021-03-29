package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Motor;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.repository.AutoRepository;
import ru.ncedu.repository.BrandRepository;
import ru.ncedu.repository.MotorRepository;
import ru.ncedu.service.UpdateAutoService;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateAutoServiceImp implements UpdateAutoService {

    private final AutoRepository autorepository;

    private final BrandRepository brandRepository;
    private final MotorRepository motorRepository;

    @Override
    public void updateAuto(AutoJoin autoJoin, Long autoId, Long idImage) {
        Optional<Auto> auto = Optional.ofNullable(autorepository.findById(autoId).orElse(null));
        Optional<Brand> brand = Optional.ofNullable(brandRepository.findById(auto.get().getIdBrand()).orElse(null));
        Optional<Motor> motor = Optional.ofNullable(motorRepository.findById(auto.get().getIdMotor()).orElse(null));

        brand.get().setNameBrand(autoJoin.getNameBrand());
        brand.get().setNameModel(autoJoin.getNameModel());
        brand.get().setYear(autoJoin.getYear());

        motor.get().setMotorType(autoJoin.getMotorType());
        motor.get().setVolume(autoJoin.getVolume());

        auto.get().setId(autoId);
        auto.get().setIdBrand(brand.get().getId());
        auto.get().setIdMotor(motor.get().getId());
        auto.get().setIdImage(idImage);
        auto.get().setColor(autoJoin.getColor());
        auto.get().setPrice(autoJoin.getPrice());
        auto.get().setDriveType(autoJoin.getDriveType());
        auto.get().setTransmissionType(autoJoin.getTransmissionType());
        auto.get().setBodyStyleType(autoJoin.getBodyStyleType());

        brandRepository.save(brand.get());
        motorRepository.save(motor.get());
        autorepository.save(auto.get());
    }
}
