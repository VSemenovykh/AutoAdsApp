package ru.ncedu.services;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ListAutoAdsService {

    ResponseEntity<Map<String, Object>> findAllAutoAds(int page, int size);
}
