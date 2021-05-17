package ru.ncedu.services;

import org.springframework.http.ResponseEntity;
import ru.ncedu.entity.Auto;
import ru.ncedu.model.DataAuto;

import java.util.List;

public interface AutoAdsService {

    Auto findAutoById(Long id);

    DataAuto findAutoAdsById(Long id);

    ResponseEntity<List<Auto>> findAll();

}
