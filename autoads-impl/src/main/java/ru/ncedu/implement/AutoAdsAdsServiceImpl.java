package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.model.DataAuto;
import ru.ncedu.repositories.AutoRepository;
import ru.ncedu.services.*;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AutoAdsAdsServiceImpl implements AutoAdsService {

    private final AutoRepository autorepository;

    @Override
    public Auto findAutoById(Long id) {
        Auto auto = autorepository.findById(id).orElse(null);
        return (auto != null) ? new Auto(auto.getId(),
                                         auto.getIdUser(),
                                         auto.getIdImage(),
                                         auto.getIdBrand(),
                                         auto.getIdContact(),
                                         auto.getIdMotor(),
                                         auto.getColor(),
                                         auto.getPrice(),
                                         auto.getDriveType(),
                                         auto.getTransmissionType(),
                                         auto.getBodyStyleType(),
                                         auto.getAddingDate()) : null;
    }

    @Override
    public DataAuto findAutoAdsById(Long id) {
        Auto auto = autorepository.findById(id).orElse(null);
        return (auto != null) ? new DataAuto(auto.getId(),
                                             auto.getIdImage(),
                                             auto.getPictureAuto().getRaster(),
                                             auto.getUser().getUsername(),
                                             auto.getContact().getEmail(),
                                             auto.getContact().getPhone(),
                                             auto.getBrand().getNameBrand(),
                                             auto.getBrand().getNameModel(),
                                             auto.getBrand().getYear(),
                                             auto.getColor(),
                                             auto.getPrice(),
                                             auto.getMotor().getMotorType(),
                                             auto.getMotor().getVolume(),
                                             auto.getDriveType(),
                                             auto.getTransmissionType(),
                                             auto.getBodyStyleType(),
                                             auto.getAddingDate()) : null;
    }

    @Override
    public ResponseEntity<List<Auto>> findAll() {
        List<Auto> autoList = autorepository.findAll();
        return (!autoList.isEmpty()) ? new ResponseEntity<>(autoList, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
