package ru.ncedu.implement;

import org.springframework.stereotype.Service;
import ru.ncedu.model.*;
import ru.ncedu.services.ValidDataAutoAds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidDataAutoAdsImp implements ValidDataAutoAds {

    @Override
    public boolean checkDataAutoAds(DataAuto dataAuto){
        if (checkDataSearchAutoAdsByNameBrand(dataAuto) && checkDataSearchAutoAdsByNameModel(dataAuto) && checkDataSearchAutoAdsByYear(dataAuto) &&
            checkDataSearchAutoAdsByColor(dataAuto) && checkDataSearchAutoAdsByMotorType(dataAuto) && checkDataSearchAutoAdsByVolume(dataAuto) &&
            checkDataSearchAutoAdsByDrive(dataAuto) && checkDataSearchAutoAdsByTransmission(dataAuto) && checkDataSearchAutoAdsByBodyStyle(dataAuto) &&
            EmailValidator(dataAuto.getEmail()) && PhoneValidator(dataAuto.getPhone())) {

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkDataSearchAutoAdsByNameBrand(DataAuto dataAuto) {
        List<String> nameBrandList = new ArrayList<>(Arrays.asList("AUDI", "FORD", "HONDA", "HYUNDAI", "BMW", "MERCEDES-BENZ", "KIA"));
        for (String brand : nameBrandList) {
            if ((brand.equals(dataAuto.getNameBrand()))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkDataSearchAutoAdsByNameModel(DataAuto dataAuto){
        List<String> nameModelList = new ArrayList<>(Arrays.asList("A3", "A4", "A8", "FIESTA", "FOCUS", "MONDEO", "ACCORD", "CROSSTOUR", "HONDA", "SOLARIS", "ELANTRA", "SONATA", "M8",
                "M5", "M6", "GLE AMG", "AMG GT", "MAYBACH GLS", "CERATO", "KIA", "KIA"));
        for (String model : nameModelList) {
            if ((model.equals(dataAuto.getNameModel()))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkDataSearchAutoAdsByYear(DataAuto dataAuto) {
        List<String> yearList = new ArrayList<>(Arrays.asList("2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010",
                                                              "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"));

        for (String year : yearList) {
            if ((year.equals(dataAuto.getYear()))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkDataSearchAutoAdsByColor(DataAuto dataAuto){
        Color[] colors = Color.values();
        for (int i = 0; i < Color.values().length; i++) {
            if (!(colors[i].name().equals(dataAuto.getColor()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDataSearchAutoAdsByMotorType(DataAuto dataAuto){
        Fuel[] fuels = Fuel.values();
        for (int i = 0; i < Fuel.values().length; i++) {
            if (!(fuels[i].name().equals(dataAuto.getMotorType()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDataSearchAutoAdsByVolume(DataAuto dataAuto){
        List<Double> volumeList = new ArrayList<>(Arrays.asList(0.2, 0.4, 0.6, 0.8, 1.0, 1.2, 1.4, 1.6, 1.8, 2.0, 2.2, 2.4, 2.6, 2.7, 2.8, 3.0, 3.2, 4.0, 5.0, 5.5));
        for (Double volume : volumeList) {
            if ((volume.equals(dataAuto.getVolume()))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkDataSearchAutoAdsByDrive(DataAuto dataAuto) {
        Drive[] drives = Drive.values();
        for (int i = 0; i < Drive.values().length; i++) {
            if (!(drives[i].name().equals(dataAuto.getDriveType()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDataSearchAutoAdsByTransmission(DataAuto dataAuto){
        Transmission[] transmissions = Transmission.values();
        for (int i = 0; i < Transmission.values().length; i++) {
            if (!(transmissions[i].name().equals(dataAuto.getTransmissionType()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDataSearchAutoAdsByBodyStyle(DataAuto dataAuto) {
        BodyStyle[] bodyStyles = BodyStyle.values();
        for (int i = 0; i < BodyStyle.values().length; i++) {
            if (!(bodyStyles[i].name().equals(dataAuto.getBodyStyleType()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean EmailValidator(String email) {
        final String EMAIL_PATTERN = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";

        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        return matcher.matches();
    }

    @Override
    public boolean PhoneValidator(String phone) {
        final String PHONE_PATTERN = "^(\\+7)\\([0-9]{3}\\)+\\-[0-9]{3}\\-[0-9]{2}-[0-9]{2}$";

        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(phone);

        return matcher.matches();
    }

}
