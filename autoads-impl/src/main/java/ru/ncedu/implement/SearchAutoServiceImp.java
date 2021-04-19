package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.ncedu.model.DataAuto;
import ru.ncedu.repository.AutoRepository;
import ru.ncedu.service.SearchAutoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchAutoServiceImp implements SearchAutoService {

    private final AutoRepository autorepository;

    @Override
    public ResponseEntity<Map<String, Object>> searchAutoPage(List<String> nameBrand,
                                                              List<String> nameModel,
                                                              String startYear,
                                                              String endYear,
                                                              List<String> color,
                                                              Double startPrice,
                                                              Double endPrice,
                                                              List<String> motorType,
                                                              Double startVolume,
                                                              Double endVolume,
                                                              List<String> drive,
                                                              List<String> transmission,
                                                              List<String> bodyStyle,
                                                              int page,
                                                              int size){
        log.info("SearchAutoServiceImp -> searchAutoPage()");
        log.info("SearchAutoServiceImp -> page: " + page);
        log.info("SearchAutoServiceImp -> size: " + size);
        try {
            List<DataAuto> listDataAuto;
            Pageable paging = PageRequest.of(page, size);

            Page<DataAuto> pageTuts;
            nameBrand = CollectionUtils.isEmpty(nameBrand) ? null : nameBrand;
            nameModel = CollectionUtils.isEmpty(nameModel) ? null : nameModel;
            color = CollectionUtils.isEmpty(color) ? null : color;
            motorType = CollectionUtils.isEmpty(motorType) ? null : motorType;
            drive = CollectionUtils.isEmpty(drive) ? null : drive;
            transmission = CollectionUtils.isEmpty(transmission) ? null : transmission;
            bodyStyle = CollectionUtils.isEmpty(bodyStyle) ? null : bodyStyle;
            log.info("SearchAutoServiceImp -> List nameBrand -> isEmpty: " + nameBrand);
            log.info("SearchAutoServiceImp -> List nameModel -> isEmpty: " + nameModel);
            log.info("SearchAutoServiceImp -> List color -> isEmpty: " + color);
            log.info("SearchAutoServiceImp -> List motorType -> isEmpty: " + motorType);
            log.info("SearchAutoServiceImp -> List drive -> isEmpty: " + drive);
            log.info("SearchAutoServiceImp -> List transmission -> isEmpty: " + transmission);
            log.info("SearchAutoServiceImp -> List bodyStyle -> isEmpty: " + bodyStyle);

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

            listDataAuto = pageTuts.getContent();
            log.info("SearchAutoServiceImp -> List<DataAuto> -> isEmpty: " + listDataAuto.isEmpty());
            if (listDataAuto.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
