package ru.ncedu.service;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface SearchAutoService {

    ResponseEntity<Map<String, Object>> multipleSearchAutoPage(List<String> nameBrand,
                                                               List<String> nameModel,
                                                               String startYear,
                                                               String endYear,
                                                               List<String> color,
                                                               Double startPrice,
                                                               Double endPrice,
                                                               List<String> motorType,
                                                               Double startVolume,
                                                               Double endVolume,
                                                               List<String> drive,
                                                               List<String> transmission,
                                                               List<String> bodyStyle,
                                                               int page,
                                                               int size);
}
