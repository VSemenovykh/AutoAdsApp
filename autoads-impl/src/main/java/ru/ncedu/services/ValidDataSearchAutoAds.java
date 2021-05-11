package ru.ncedu.services;

import ru.ncedu.model.*;

public interface ValidDataSearchAutoAds {

    boolean checkDataSearchAutoAds(DataSearchAuto dataSearchAuto);

    boolean checkDataSearchAutoAdsByNameBrand(DataSearchAuto dataSearchAuto);

    boolean checkDataSearchAutoAdsByNameModel(DataSearchAuto dataSearchAuto);

    boolean checkDataSearchAutoAdsByStartYear(DataSearchAuto dataSearchAuto);

    boolean checkDataSearchAutoAdsByEndYear(DataSearchAuto dataSearchAuto);

    boolean checkDataSearchAutoAdsByColor(DataSearchAuto dataSearchAuto);

    boolean checkDataSearchAutoAdsByMotorType(DataSearchAuto dataSearchAuto);

    boolean checkDataSearchAutoAdsByStartVolume(DataSearchAuto dataSearchAuto);

    boolean checkDataSearchAutoAdsByEndVolume(DataSearchAuto dataSearchAuto);

    boolean checkDataSearchAutoAdsByDrive(DataSearchAuto dataSearchAuto);

    boolean checkDataSearchAutoAdsByTransmission(DataSearchAuto dataSearchAuto);

    boolean checkDataSearchAutoAdsByBodyStyle(DataSearchAuto dataSearchAuto);
}
