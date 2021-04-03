package ru.ncedu.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import ru.ncedu.model.AutoJoin;

import java.util.List;
import java.util.Map;

public interface ListAutoService {

    List<AutoJoin> getListAuto();

    ResponseEntity<Map<String, Object>> findAllAutoJoinPage(int page, int size);
}
