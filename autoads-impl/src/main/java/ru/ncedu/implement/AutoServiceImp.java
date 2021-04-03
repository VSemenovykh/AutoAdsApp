package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Motor;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.repository.AutoRepository;
import ru.ncedu.service.AutoService;
import ru.ncedu.service.BrandService;
import ru.ncedu.service.PictureAutoService;
import ru.ncedu.service.MotorService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AutoServiceImp implements AutoService {

    private final AutoRepository autorepository;

    private final BrandService brandService;

    private final MotorService motorService;

    private final PictureAutoService imageAutoService;

    @Override
    public Auto findById(Long id) {
        Auto auto = autorepository.findById(id).orElse(null);

        return new Auto( auto.getId(),
                         auto.getIdImage(),
                         auto.getIdBrand(),
                         auto.getIdMotor(),
                         auto.getColor(),
                         auto.getPrice(),
                         auto.getDriveType(),
                         auto.getTransmissionType(),
                         auto.getBodyStyleType() );
    }

    @Override
    public AutoJoin findAutoJoinById(Long id){
        Auto auto = autorepository.findById(id).orElse(null);

        if( auto != null){
            Brand brand = brandService.findById(auto.getIdBrand());
            Motor motor = motorService.findById(auto.getIdMotor());

            byte[] raster = null;

            if(auto.getIdImage() != null){
                raster = imageAutoService.findPictureAutoById(auto.getIdImage()).getRaster();
            }

            return new AutoJoin( auto.getId(),
                                 auto.getIdImage(),
                                 raster,
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
        }else {
            return  null;
        }
    }

    @Override
    public List<AutoJoin> searchAuto( String nameBrand,
                                      String nameModel,
                                      String startYear,
                                      String endYear,
                                      String color,
                                      Double startPrice,
                                      Double endPrice,
                                      String motorType,
                                      Double startVolume,
                                      Double endVolume,
                                      String drive,
                                      String transmission,
                                      String bodyStyle ) {

        return autorepository.searchAuto( nameBrand,
                                          nameModel,
                                          startYear,
                                          endYear,
                                          color,
                                          startPrice,
                                          endPrice,
                                          motorType,
                                          startVolume,
                                          endVolume,
                                          drive,
                                          transmission,
                                          bodyStyle);
    }
    @Override
    public ResponseEntity<Map<String, Object>> searchAutoPage(String nameBrand,
                                                       String nameModel,
                                                       String startYear,
                                                       String endYear,
                                                       String color,
                                                       Double startPrice,
                                                       Double endPrice,
                                                       String motorType,
                                                       Double startVolume,
                                                       Double endVolume,
                                                       String drive,
                                                       String transmission,
                                                       String bodyStyle,
                                                       int page,
                                                       int size ){

        try {
            List<AutoJoin> listAutoJoin;
            Pageable paging = PageRequest.of(page, size);

            Page<AutoJoin> pageTuts;
            pageTuts = autorepository.searchAutoPage(nameBrand,
                                                     nameModel,
                                                     startYear,
                                                     endYear,
                                                     color,
                                                     startPrice,
                                                     endPrice,
                                                     motorType,
                                                     startVolume,
                                                     endVolume,
                                                     drive,
                                                     transmission,
                                                     bodyStyle,
                                                     paging);

            listAutoJoin = pageTuts.getContent();

            if (listAutoJoin.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("listAutoJoin", listAutoJoin);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalAutoJoin", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
