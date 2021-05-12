package ru.ncedu.services;

import ru.ncedu.entity.CompareAuto;

public interface CompareAutoAdsService {

    CompareAuto findCompareAutoAdsByIdAuto(Long idAuto, Long idUser);
}
