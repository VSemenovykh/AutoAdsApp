package ru.ncedu.implement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.ncedu.model.*;
import ru.ncedu.services.ValidDataAutoAds;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidDataAutoAdsImpl implements ValidDataAutoAds {

    @Value("${autoads.years}")
    private List<String> yearList;
    @Value("${autoads.model.names}")
    private List<String> nameModelList;
    @Value("${autoads.brand.names}")
    private List<String> nameBrandList;
    @Value("${autoads.volumes}")
    private List<Double> volumeList;

    private final String EMAIL_PATTERN = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
    private final String PHONE_PATTERN = "^(\\+7)\\([0-9]{3}\\)+\\-[0-9]{3}\\-[0-9]{2}-[0-9]{2}$";

    @Override
    public boolean checkDataAutoAds(DataAuto dataAuto) {
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
        for (String brand : nameBrandList) {
            if ((brand.equals(dataAuto.getNameBrand()))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkDataSearchAutoAdsByNameModel(DataAuto dataAuto) {
        for (String model : nameModelList) {
            if ((model.equals(dataAuto.getNameModel()))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkDataSearchAutoAdsByYear(DataAuto dataAuto) {
        for (String year : yearList) {
            if ((year.equals(dataAuto.getYear()))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkDataSearchAutoAdsByColor(DataAuto dataAuto) {
        Color[] colors = Color.values();
        for (int i = 0; i < Color.values().length; i++) {
            if (!(colors[i].name().equals(dataAuto.getColor()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDataSearchAutoAdsByMotorType(DataAuto dataAuto) {
        Fuel[] fuels = Fuel.values();
        for (int i = 0; i < Fuel.values().length; i++) {
            if (!(fuels[i].name().equals(dataAuto.getMotorType()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDataSearchAutoAdsByVolume(DataAuto dataAuto) {
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
    public boolean checkDataSearchAutoAdsByTransmission(DataAuto dataAuto) {
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
        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        return matcher.matches();
    }

    @Override
    public boolean PhoneValidator(String phone) {

        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(phone);

        return matcher.matches();
    }

}

