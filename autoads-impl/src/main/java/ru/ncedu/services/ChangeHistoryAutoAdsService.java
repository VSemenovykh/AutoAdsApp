package ru.ncedu.services;

import org.springframework.http.ResponseEntity;
import ru.ncedu.entity.ChangeHistoryAutoAds;

public interface ChangeHistoryAutoAdsService {

    ResponseEntity<ChangeHistoryAutoAds> findAllChangeAutoAds(Long idAuto);
}
