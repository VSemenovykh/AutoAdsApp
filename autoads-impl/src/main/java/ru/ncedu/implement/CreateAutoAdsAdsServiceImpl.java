package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.entity.*;
import ru.ncedu.model.DataAuto;
import ru.ncedu.repositories.*;
import ru.ncedu.services.CreateAutoAdsService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static org.springframework.util.StringUtils.isEmpty;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CreateAutoAdsAdsServiceImpl implements CreateAutoAdsService {

    private final AutoRepository autorepository;
    private final BrandRepository brandRepository;
    private final MotorRepository motorRepository;
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<DataAuto> saveAuto(DataAuto dataAuto, Long idImage) {
        Date date = new GregorianCalendar().getTime();
        String formattedDate = new SimpleDateFormat("d/M/yyyy/ HH:mm:ss", Locale.US).format(date);

        if (!isEmpty(dataAuto)) {
            Brand brand = brandRepository.save(new Brand(null, dataAuto.getNameBrand(), dataAuto.getNameModel(), dataAuto.getYear()));
            Motor motor = motorRepository.save(new Motor(null, dataAuto.getMotorType(), dataAuto.getVolume()));
            Contact contact = contactRepository.save(new Contact(null, dataAuto.getEmail(), dataAuto.getPhone()));
            User user = userRepository.findByUsername(dataAuto.getUsername()).orElse(null);

            if(user != null){
                Auto auto = new Auto(dataAuto.getId(),
                                     user.getId(),
                                     idImage,
                                     brand.getId(),
                                     contact.getId(),
                                     motor.getId(),
                                     dataAuto.getColor(),
                                     dataAuto.getPrice(),
                                     dataAuto.getDriveType(),
                                     dataAuto.getTransmissionType(),
                                     dataAuto.getBodyStyleType(),
                                     formattedDate);

                autorepository.save(auto);

                return new ResponseEntity<DataAuto>(new DataAuto(auto.getId(),
                                                                 auto.getIdImage(),
                                                                 null,
                                                                 user.getUsername(),
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
                                                                 auto.getBodyStyleType(),
                                                                 formattedDate),
                                                        HttpStatus.CREATED);
            }else{
                return new ResponseEntity<DataAuto>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return null;
        }
    }
}
