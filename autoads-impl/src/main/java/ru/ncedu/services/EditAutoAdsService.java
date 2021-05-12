package ru.ncedu.services;

import ru.ncedu.model.DataAuto;

public interface EditAutoAdsService {

    void editAutoAds(DataAuto auto, Long autoId, Long idImage);
}
