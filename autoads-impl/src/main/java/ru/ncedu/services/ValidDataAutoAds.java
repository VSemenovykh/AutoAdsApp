package ru.ncedu.services;

import ru.ncedu.model.DataAuto;

public interface ValidDataAutoAds {

    boolean checkDataAutoAds(DataAuto dataAuto);

    boolean checkDataAutoAdsByNameBrand(DataAuto dataAuto);

    boolean checkDataAutoAdsByNameModel(DataAuto dataAuto);

    boolean checkDataAutoAdsByYear(DataAuto dataAuto);

    boolean checkDataAutoAdsByColor(DataAuto dataAutouto);

    boolean checkDataAutoAdsByMotorType(DataAuto dataAutoo);

    boolean checkDataAutoAdsByVolume(DataAuto dataAuto);

    boolean checkDataAutoAdsByDrive(DataAuto dataAuto);

    boolean checkDataAutoAdsByTransmission(DataAuto dataAutoo);

    boolean checkDataAutoAdsByBodyStyle(DataAuto dataAuto);

    boolean EmailValidator(String email);

    boolean PhoneValidator(String phone);

    boolean DateValidator(String date);
}
