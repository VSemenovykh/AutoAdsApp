package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.ChangeHistoryAutoAds;
import ru.ncedu.repositories.ChangeHistoryAutoAdsRepository;
import ru.ncedu.services.ChangeHistoryAutoAdsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChangeHistoryAutoAdsServiceImpl implements ChangeHistoryAutoAdsService {

    private final ChangeHistoryAutoAdsRepository changeHistoryAutoAdsRepository;

    @Override
    public ResponseEntity<Map<String, Object>> findAllChangeAutoAds(int page, int size, Long idAuto) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<ChangeHistoryAutoAds> pageTuts = changeHistoryAutoAdsRepository.findAllByIdAuto(paging, idAuto);

            List<ChangeHistoryAutoAds> changeHistoryAutoAdsServiceList = pageTuts.getContent();

            if (changeHistoryAutoAdsServiceList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("changeHistoryAutoAdsList", changeHistoryAutoAdsServiceList);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalAutoJoin", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
