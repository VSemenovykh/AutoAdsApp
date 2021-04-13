package ru.ncedu.service;

import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface ListAutoService {

    ResponseEntity<Map<String, Object>> findAllAutoJoinPage(int page, int size);
}
