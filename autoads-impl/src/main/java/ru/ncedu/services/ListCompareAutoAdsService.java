package ru.ncedu.services;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ListCompareAutoAdsService {

    ResponseEntity<Map<String, Object>> findAllAutoAdsForCompare(int page, int size, Long idUser);
}
