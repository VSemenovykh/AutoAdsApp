package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Contact;
import ru.ncedu.entity.Motor;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.repository.AutoRepository;
import ru.ncedu.repository.BrandRepository;
import ru.ncedu.repository.ContactRepository;
import ru.ncedu.repository.MotorRepository;
import ru.ncedu.service.CreateAutoService;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateAutoServiceImp implements CreateAutoService {

    private final AutoRepository autorepository;

    private final BrandRepository brandRepository;

    private final MotorRepository motorRepository;

    private final ContactRepository contactRepository;

    @Override
    public AutoJoin saveAuto(AutoJoin autoJoin, Long idImage) {
        Brand brand = brandRepository.save(new Brand(null,
                                                        autoJoin.getNameBrand(),
                                                        autoJoin.getNameModel(),
                                                        autoJoin.getYear() ));
        Motor motor = motorRepository.save(new Motor(null,
                                                         autoJoin.getMotorType(),
                                                         autoJoin.getVolume() ));

        Contact contact = contactRepository.save(new Contact(null, autoJoin.getEmail(),autoJoin.getPhone()));

        Auto auto = new Auto( autoJoin.getId(),
                              idImage,
                              brand.getId(),
                              contact.getId(),
                              motor.getId(),
                              autoJoin.getColor(),
                              autoJoin.getPrice(),
                              autoJoin.getTransmissionType(),
                              autoJoin.getDriveType(),
                              autoJoin.getBodyStyleType() );

        autorepository.save(auto);

        AutoJoin newAutoJoin = new AutoJoin( auto.getId(),
                                             auto.getIdImage(),
                                            null,
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
        return newAutoJoin;
    }
}
