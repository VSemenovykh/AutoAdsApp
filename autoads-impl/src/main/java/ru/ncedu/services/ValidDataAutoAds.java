package ru.ncedu.services;

import ru.ncedu.model.DataAuto;
import ru.ncedu.model.DataSearchAuto;

public interface ValidDataAutoAds {

    boolean checkDataAutoAds(DataAuto dataAuto);

    boolean checkDataSearchAutoAdsByNameBrand(DataAuto dataAuto);

    boolean checkDataSearchAutoAdsByNameModel(DataAuto dataAuto);

    boolean checkDataSearchAutoAdsByYear(DataAuto dataAuto);

    boolean checkDataSearchAutoAdsByColor(DataAuto dataAutouto);

    boolean checkDataSearchAutoAdsByMotorType(DataAuto dataAutoo);

    boolean checkDataSearchAutoAdsByVolume(DataAuto dataAuto);

    boolean checkDataSearchAutoAdsByDrive(DataAuto dataAuto);

    boolean checkDataSearchAutoAdsByTransmission(DataAuto dataAutoo);

    boolean checkDataSearchAutoAdsByBodyStyle(DataAuto dataAuto);

    boolean EmailValidator(String email);

    boolean PhoneValidator(String phone);
}
