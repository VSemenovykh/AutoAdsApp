package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Contact;
import ru.ncedu.entity.Motor;
import ru.ncedu.model.DataAuto;
import ru.ncedu.interfaces.AutoRepository;
import ru.ncedu.interfaces.BrandRepository;
import ru.ncedu.interfaces.ContactRepository;
import ru.ncedu.interfaces.MotorRepository;
import ru.ncedu.services.CreateAutoService;
import static org.springframework.util.StringUtils.isEmpty;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CreateAutoServiceImp implements CreateAutoService {

    private final AutoRepository autorepository;

    private final BrandRepository brandRepository;

    private final MotorRepository motorRepository;

    private final ContactRepository contactRepository;

    @Override
    public DataAuto saveAuto(DataAuto dataAuto, Long idImage) {
        if (!isEmpty(dataAuto)) {
            Brand brand = brandRepository.save(new Brand(null, dataAuto.getNameBrand(), dataAuto.getNameModel(), dataAuto.getYear()));
            Motor motor = motorRepository.save(new Motor(null, dataAuto.getMotorType(), dataAuto.getVolume()));
            Contact contact = contactRepository.save(new Contact(null, dataAuto.getEmail(), dataAuto.getPhone()));

            Auto auto = new Auto(dataAuto.getId(),
                                 idImage,
                                 brand.getId(),
                                 contact.getId(),
                                 motor.getId(),
                                 dataAuto.getColor(),
                                 dataAuto.getPrice(),
                                 dataAuto.getDriveType(),
                                 dataAuto.getTransmissionType(),
                                 dataAuto.getBodyStyleType());

            autorepository.save(auto);

            return new DataAuto(auto.getId(),
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
                                auto.getBodyStyleType());
        } else {
            return null;
        }
    }
}
