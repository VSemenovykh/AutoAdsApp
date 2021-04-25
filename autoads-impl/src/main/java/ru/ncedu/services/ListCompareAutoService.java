package ru.ncedu.services;

import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface ListCompareAutoService {

    ResponseEntity<Map<String, Object>> findAllAutoComparePage(int page, int size, Long idUser);
}
