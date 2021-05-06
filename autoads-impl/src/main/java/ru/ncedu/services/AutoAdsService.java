package ru.ncedu.services;

import ru.ncedu.entity.Auto;
import ru.ncedu.model.DataAuto;

public interface AutoAdsService {

    Auto findAutoById(Long id);

    DataAuto findAutoAdsById(Long id);
}
