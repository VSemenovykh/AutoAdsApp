package ru.ncedu.service;

import org.springframework.http.ResponseEntity;
import ru.ncedu.entity.Auto;
import ru.ncedu.model.AutoJoin;
import java.util.List;
import java.util.Map;

public interface AutoService {

    Auto findById(Long id);

    AutoJoin findAutoJoinById(Long id);

    List<AutoJoin> searchAuto( String nameBrand,
                               String nameModel,
                               String startYear,
                               String endYear,
                               String color,
                               Double startPrice,
                               Double endPrice,
                               String motorType,
                               Double startVolume,
                               Double endVolume,
                               String drive,
                               String transmission,
                               String bodyStyle );

    ResponseEntity<Map<String, Object>> searchAutoPage(String nameBrand,
                                                   String nameModel,
                                                   String startYear,
                                                   String endYear,
                                                   String color,
                                                   Double startPrice,
                                                   Double endPrice,
                                                   String motorType,
                                                   Double startVolume,
                                                   Double endVolume,
                                                   String drive,
                                                   String transmission,
                                                   String bodyStyle,
                                                   int page,
                                                   int size);
}
