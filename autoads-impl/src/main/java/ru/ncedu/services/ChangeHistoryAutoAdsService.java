package ru.ncedu.services;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ChangeHistoryAutoAdsService {

    ResponseEntity<Map<String, Object>> findAllChangeAutoAds(int page, int size, Long idAuto);
}
