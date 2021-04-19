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
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Contact;
import ru.ncedu.entity.Motor;
import ru.ncedu.model.DataAuto;
import ru.ncedu.repository.AutoRepository;
import ru.ncedu.service.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListAutoServiceImp implements ListAutoService {

    private final AutoRepository autorepository;

    private final BrandService brandService;

    private final MotorService motorService;

    private final ContactService contactService;

    private final PictureAutoService imageAutoService;

    @Override
    public ResponseEntity<Map<String, Object>> findAllAutoJoinPage(int page, int size){
        log.info("ListAutoServiceImp -> findAllAutoJoinPage()");
        log.info("ListAutoServiceImp -> page: " + page);
        log.info("ListAutoServiceImp -> size: " + size);
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Auto> pageTuts = autorepository.findAll(paging);

            List<Auto> autoList = pageTuts.getContent();
            log.info("ListAutoServiceImp -> List<Auto> isEmpty: " + autoList.isEmpty());
            List<DataAuto> listDataAuto = new ArrayList<>();

            if (autoList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            String brandName;
            String modelName;
            String year;
            String motorType;
            double volume;
            String email;
            String phone;

            for (Auto auto : autoList) {
                Brand brand = brandService.findById(auto.getIdBrand());
                Motor motor = motorService.findById(auto.getIdMotor());
                Contact contact = contactService.findById(auto.getIdContact());
                log.info("ListAutoServiceImp -> Brand isNull: " + isNull(brand));
                log.info("ListAutoServiceImp -> Motor isNull: " + isNull(motor));
                log.info("ListAutoServiceImp -> Contact isNull: " + isNull(contact));

                byte[] raster = null;

                if(auto.getIdImage() != null){
                    raster = imageAutoService.findPictureAutoById(auto.getIdImage()).getRaster();
                }

                brandName = brand.getNameBrand();
                modelName = brand.getNameModel();
                year = brand.getYear();

                motorType = motor.getMotorType();
                volume = motor.getVolume();

                email = contact.getEmail();
                phone = contact.getPhone();

                DataAuto dataAuto = new DataAuto(auto.getId(),
                                                 auto.getIdImage(),
                                                 raster,
                                                 email,
                                                 phone,
                                                 brandName,
                                                 modelName,
                                                 year,
                                                 auto.getColor(),
                                                 auto.getPrice(),
                                                 motorType,
                                                 volume,
                                                 auto.getDriveType(),
                                                 auto.getTransmissionType(),
                                                 auto.getBodyStyleType());

                listDataAuto.add(dataAuto);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("listAutoJoin", listDataAuto);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalAutoJoin", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
