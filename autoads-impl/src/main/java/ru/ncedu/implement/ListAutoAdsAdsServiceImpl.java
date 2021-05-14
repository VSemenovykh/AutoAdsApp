package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Auto;
import ru.ncedu.model.DataAuto;
import ru.ncedu.repositories.AutoRepository;
import ru.ncedu.services.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListAutoAdsAdsServiceImpl implements ListAutoAdsService {

    private final AutoRepository autorepository;

    @Override
    public ResponseEntity<Map<String, Object>> findAllAutoAds(int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Auto> pageTuts = autorepository.findAll(paging);
            List<Auto> autoList = pageTuts.getContent();
            if (autoList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<DataAuto> listDataAuto = new ArrayList<>();
            for (Auto auto : autoList) {
                DataAuto dataAuto = new DataAuto(auto.getId(),
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
                                                 auto.getAddingDate());

                listDataAuto.add(dataAuto);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("listAutoJoin", listDataAuto);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalAutoJoin", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
