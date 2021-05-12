package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.*;
import ru.ncedu.model.DataCompareAuto;
import ru.ncedu.repositories.CompareAutoRepository;
import ru.ncedu.services.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListCompareAutoAdsAdsServiceImpl implements ListCompareAutoAdsService {

    private final CompareAutoRepository compareAutoRepository;
    private final AutoAdsService autoAdsService;
    private final BrandService brandService;
    private final MotorService motorService;
    private final ContactService contactService;
    private final PictureAutoService imageAutoService;

    @Override
    public ResponseEntity<Map<String, Object>> findAllAutoAdsForCompare(int page, int size, Long idUser) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<CompareAuto> pageTuts = compareAutoRepository.findAllByIdUser(paging, idUser);

            List<CompareAuto> compareAutoList = pageTuts.getContent();


            if (compareAutoList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<DataCompareAuto> newListAutoJoin = new ArrayList<>();

            String brandName;
            String modelName;
            String year;
            String motorType;
            double volume;
            String email;
            String phone;

            for (CompareAuto compareAuto : compareAutoList) {
                Auto auto = autoAdsService.findAutoById(compareAuto.getIdAuto());
                Brand brand = brandService.findById(auto.getIdBrand());
                Motor motor = motorService.findById(auto.getIdMotor());
                Contact contact = contactService.findById(auto.getIdContact());

                byte[] raster = null;

                if (auto.getIdImage() != null) {
                    raster = imageAutoService.findPictureAutoById(auto.getIdImage()).getRaster();
                }

                brandName = brand.getNameBrand();
                modelName = brand.getNameModel();
                year = brand.getYear();

                motorType = motor.getMotorType();
                volume = motor.getVolume();

                email = contact.getEmail();
                phone = contact.getPhone();

                DataCompareAuto newDataCompareAuto = new DataCompareAuto(auto.getId(),
                                                                         raster,
                                                                         brandName,
                                                                         modelName,
                                                                         year,
                                                                         auto.getColor(),
                                                                         auto.getPrice(),
                                                                         motorType,
                                                                         volume,
                                                                         auto.getDriveType(),
                                                                         auto.getTransmissionType(),
                                                                         auto.getBodyStyleType(),
                                                                         email,
                                                                         phone);

                newListAutoJoin.add(newDataCompareAuto);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("listAutoJoin", newListAutoJoin);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalAutoJoin", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
