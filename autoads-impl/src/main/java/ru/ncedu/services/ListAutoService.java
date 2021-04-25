package ru.ncedu.services;

import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface ListAutoService {

    ResponseEntity<Map<String, Object>> findAllAutoJoinPage(int page, int size);
}
