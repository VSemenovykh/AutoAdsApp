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
import ru.ncedu.repository.CompareAutoRepository;
import ru.ncedu.service.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static org.aspectj.util.LangUtil.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListCompareAutoServiceImp implements ListCompareAutoService{

    private final CompareAutoRepository compareAutoRepository;

    private final AutoService autoService;

    private final BrandService brandService;

    private final MotorService motorService;

    private final ContactService contactService;

    private final PictureAutoService imageAutoService;

    @Override
    public ResponseEntity<Map<String, Object>> findAllAutoComparePage(int page, int size) {
        log.info("ListCompareAutoServiceImp -> findAllAutoComparePage()");
        log.info("ListCompareAutoServiceImp -> page: " + page);
        log.info("ListCompareAutoServiceImp -> size: " + size);
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<CompareAuto> pageTuts = compareAutoRepository.findAll(paging);

            List<CompareAuto> compareAutoList = pageTuts.getContent();
            log.info("ListCompareAutoServiceImp -> List<CompareAuto> -> isEmpty: " + compareAutoList.isEmpty());
            List<DataCompareAuto> newListAutoJoin = new ArrayList<>();

            if (compareAutoList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            String brandName;
            String modelName;
            String year;
            String motorType;
            double volume;
            String email;
            String phone;

            for (CompareAuto compareAuto : compareAutoList) {
                Auto auto = autoService.findById(compareAuto.getIdAuto());
                Brand brand = brandService.findById(auto.getIdBrand());
                Motor motor = motorService.findById(auto.getIdMotor());
                Contact contact = contactService.findById(auto.getIdContact());
                log.info("ListCompareAutoServiceImp -> Auto -> isNull: " + isNull(auto));
                log.info("ListCompareAutoServiceImp -> Brand -> isNull: " + isNull(brand));
                log.info("ListCompareAutoServiceImp -> Motor -> isNull: " + isNull(motor));
                log.info("ListCompareAutoServiceImp -> Contact -> isNull: " + isNull(contact));

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
