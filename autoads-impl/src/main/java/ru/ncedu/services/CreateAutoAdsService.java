package ru.ncedu.services;

import ru.ncedu.model.DataAuto;

public interface CreateAutoAdsService {

    DataAuto saveAuto(DataAuto dataAuto, Long idImage);
}
