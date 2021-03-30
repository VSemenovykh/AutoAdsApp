package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        autorepository.findById(autoId)
                .ifPresent(auto -> {
                    Optional<Brand> brand = brandRepository.findById(auto.getIdBrand());
                    Optional<Motor> motor = motorRepository.findById(auto.getIdMotor());

                    brand.get().setNameBrand(autoJoin.getNameBrand());
                    brand.get().setNameModel(autoJoin.getNameModel());
                    brand.get().setYear(autoJoin.getYear());

                    motor.get().setMotorType(autoJoin.getMotorType());
                    motor.get().setVolume(autoJoin.getVolume());

                    auto.setId(autoId);
                    auto.setIdBrand(brand.get().getId());
                    auto.setIdMotor(motor.get().getId());
                    auto.setIdImage(idImage);
                    auto.setColor(autoJoin.getColor());
                    auto.setPrice(autoJoin.getPrice());
                    auto.setDriveType(autoJoin.getDriveType());
                    auto.setTransmissionType(autoJoin.getTransmissionType());
                    auto.setBodyStyleType(autoJoin.getBodyStyleType());
                });
    }
}
