package ru.ncedu.services;

import ru.ncedu.entity.CompareAuto;
import ru.ncedu.model.DataAuto;

public interface CreateListCompareAutoAdsService {

    CompareAuto addAutoAdsToListCompare(DataAuto dataAuto, Long idUser);
}
