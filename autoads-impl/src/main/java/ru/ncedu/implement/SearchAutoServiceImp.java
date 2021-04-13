package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.repository.AutoRepository;
import ru.ncedu.service.SearchAutoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        try {
            List<AutoJoin> listAutoJoin;
            Pageable paging = PageRequest.of(page, size);

            Page<AutoJoin> pageTuts;
            nameBrand = CollectionUtils.isEmpty(nameBrand) ? null : nameBrand;
            nameModel = CollectionUtils.isEmpty(nameModel) ? null : nameModel;
            color = CollectionUtils.isEmpty(color) ? null : color;
            motorType = CollectionUtils.isEmpty(motorType) ? null : motorType;
            drive = CollectionUtils.isEmpty(drive) ? null : drive;
            transmission = CollectionUtils.isEmpty(transmission) ? null : transmission;
            bodyStyle = CollectionUtils.isEmpty(bodyStyle) ? null : bodyStyle;

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
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
