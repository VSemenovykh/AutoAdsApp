package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Contact;
import ru.ncedu.entity.Motor;
import ru.ncedu.model.DataAuto;
import ru.ncedu.repository.AutoRepository;
import ru.ncedu.repository.BrandRepository;
import ru.ncedu.repository.ContactRepository;
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

    private final ContactRepository contactRepository;

    @Override
    public void updateAuto(DataAuto dataAuto, Long autoId, Long idImage) {
        autorepository.findById(autoId)
                .ifPresent(auto -> {
                    Optional<Brand> brand = brandRepository.findById(auto.getIdBrand());
                    Optional<Motor> motor = motorRepository.findById(auto.getIdMotor());
                    Optional<Contact> contact = contactRepository.findById(auto.getIdContact());

                    brand.get().setNameBrand(dataAuto.getNameBrand());
                    brand.get().setNameModel(dataAuto.getNameModel());
                    brand.get().setYear(dataAuto.getYear());

                    motor.get().setMotorType(dataAuto.getMotorType());
                    motor.get().setVolume(dataAuto.getVolume());

                    if (dataAuto.getEmail() != null) {
                        contact.get().setEmail(dataAuto.getEmail());
                    }

                    if (dataAuto.getPhone() != null) {
                        contact.get().setPhone(dataAuto.getPhone());
                    }

                    auto.setId(autoId);
                    auto.setIdBrand(brand.get().getId());
                    auto.setIdMotor(motor.get().getId());
                    auto.setIdImage(idImage);
                    auto.setColor(dataAuto.getColor());
                    auto.setPrice(dataAuto.getPrice());
                    auto.setDriveType(dataAuto.getDriveType());
                    auto.setTransmissionType(dataAuto.getTransmissionType());
                    auto.setBodyStyleType(dataAuto.getBodyStyleType());
                });
    }
}
