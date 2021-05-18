package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.entity.*;
import ru.ncedu.model.DataAuto;
import ru.ncedu.repositories.*;
import ru.ncedu.services.EditAutoAdsService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EditAutoAdsAdsServiceImpl implements EditAutoAdsService {

    private final AutoRepository autorepository;
    private final BrandRepository brandRepository;
    private final MotorRepository motorRepository;
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final ChangeHistoryAutoAdsRepository changeHistoryAutoAdsRepository;

    @Override
    public void editAutoAds(DataAuto dataAuto, Long autoId, Long idImage) {
        Date date = new GregorianCalendar().getTime();
        String formattedDate = new SimpleDateFormat("d/M/yyyy/ HH:mm:ss", Locale.US).format(date);

        autorepository.findById(autoId)
                .ifPresent(auto -> {
                    Optional<Brand> brand = brandRepository.findById(auto.getIdBrand());
                    Optional<Motor> motor = motorRepository.findById(auto.getIdMotor());
                    Optional<Contact> contact = contactRepository.findById(auto.getIdContact());
                    Optional<User> user = userRepository.findByUsername(dataAuto.getUsername());

                    brand.get().setNameBrand(dataAuto.getNameBrand());
                    brand.get().setNameModel(dataAuto.getNameModel());
                    brand.get().setYear(dataAuto.getYear());

                    motor.get().setMotorType(dataAuto.getMotorType());
                    motor.get().setVolume(dataAuto.getVolume());

                    User newUser = user.get();

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

                    ChangeHistoryAutoAds modifyChangeHistoryAutoAds = changeHistoryAutoAdsRepository.findByIdAuto(autoId);
                    if (modifyChangeHistoryAutoAds != null) {
                        modifyChangeHistoryAutoAds.setId(modifyChangeHistoryAutoAds.getId());
                        modifyChangeHistoryAutoAds.setIdAuto(modifyChangeHistoryAutoAds.getIdAuto());
                        modifyChangeHistoryAutoAds.setUsername(newUser.getUsername());
                        modifyChangeHistoryAutoAds.setModifyData(formattedDate);
                        changeHistoryAutoAdsRepository.save(modifyChangeHistoryAutoAds);
                    } else {
                        ChangeHistoryAutoAds changeHistoryAutoAds = new ChangeHistoryAutoAds(null, autoId, newUser.getUsername(), formattedDate);
                        changeHistoryAutoAdsRepository.save(changeHistoryAutoAds);
                    }
                });
    }
}
