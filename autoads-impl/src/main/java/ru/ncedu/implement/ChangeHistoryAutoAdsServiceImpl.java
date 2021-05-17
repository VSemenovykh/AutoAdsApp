package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.ChangeHistoryAutoAds;
import ru.ncedu.repositories.ChangeHistoryAutoAdsRepository;
import ru.ncedu.services.ChangeHistoryAutoAdsService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChangeHistoryAutoAdsServiceImpl implements ChangeHistoryAutoAdsService {

    private final ChangeHistoryAutoAdsRepository changeHistoryAutoAdsRepository;

    @Override
    public ResponseEntity<ChangeHistoryAutoAds> findAllChangeAutoAds(Long idAuto) {
        ChangeHistoryAutoAds changeHistoryAutoAds = changeHistoryAutoAdsRepository.findByIdAuto(idAuto);
        return (changeHistoryAutoAds != null)?( new ResponseEntity<ChangeHistoryAutoAds>(changeHistoryAutoAds, HttpStatus.CREATED)):new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
