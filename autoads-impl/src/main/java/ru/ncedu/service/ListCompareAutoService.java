package ru.ncedu.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ListCompareAutoService {

    ResponseEntity<Map<String, Object>> findAllAutoComparePage(int page, int size);
}
