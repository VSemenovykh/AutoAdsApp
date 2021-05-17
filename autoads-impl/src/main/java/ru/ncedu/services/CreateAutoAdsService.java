package ru.ncedu.services;

import org.springframework.http.ResponseEntity;
import ru.ncedu.model.DataAuto;

public interface CreateAutoAdsService {

    ResponseEntity<DataAuto> saveAuto(DataAuto dataAuto, Long idImage);
}
