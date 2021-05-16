package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.ncedu.model.*;
import ru.ncedu.repositories.UserRepository;
import ru.ncedu.services.ValidDataAutoAds;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ValidDataAutoAdsImpl implements ValidDataAutoAds {

    private final UserRepository userRepository;

    @Value("${autoads.years}")
    private List<String> yearList;
    @Value("${autoads.audi.model.names}")
    private List<String> audiModelList;
    @Value("${autoads.ford.model.names}")
    private List<String> fordModelList;
    @Value("${autoads.honda.model.names}")
    private List<String> hondaModelList;
    @Value("${autoads.hyundai.model.names}")
    private List<String> hyundaiModelList;
    @Value("${autoads.mercedes.benz.model.names}")
    private List<String> mercedesBenzModelList;
    @Value("${autoads.bmw.model.names}")
    private List<String> bmwModelList;
    @Value("${autoads.kia.model.names}")
    private List<String> kiaModelList;
    @Value("${autoads.brand.names}")
    private List<String> nameBrandList;
    @Value("${autoads.volumes}")
    private List<Double> volumeList;

    private final String EMAIL_PATTERN = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
    private final String PHONE_PATTERN = "^(\\+7)\\([0-9]{3}\\)+\\-[0-9]{3}\\-[0-9]{2}-[0-9]{2}$";
    private final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)/ ([\\d]{1,2}\\:+[\\d]{1,2}\\:+[\\d]{1,2})";

    @Override
    public boolean checkDataAutoAds(DataAuto dataAuto) {
        if (checkDataAutoAdsByNameBrand(dataAuto) && checkDataAutoAdsByNameModel(dataAuto) && checkDataAutoAdsByYear(dataAuto) &&
                checkDataAutoAdsByColor(dataAuto) && checkDataAutoAdsByMotorType(dataAuto) && checkDataAutoAdsByVolume(dataAuto) &&
                checkDataAutoAdsByDrive(dataAuto) && checkDataAutoAdsByTransmission(dataAuto) && checkDataAutoAdsByBodyStyle(dataAuto) &&
                EmailValidator(dataAuto.getEmail()) && PhoneValidator(dataAuto.getPhone()) && DateValidator(dataAuto.getAddingDate())) {

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkDataAutoAdsByNameBrand(DataAuto dataAuto) {
        for (String brand : nameBrandList) {
            if ((brand.equals(dataAuto.getNameBrand()))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkDataAutoAdsByNameModel(DataAuto dataAuto) {
        for (String model : audiModelList) {
            if ((model.equals(dataAuto.getNameModel()))) {
                return true;
            }
        }

        for (String model : fordModelList) {
            if ((model.equals(dataAuto.getNameModel()))) {
                return true;
            }
        }

        for (String model : hondaModelList) {
            if ((model.equals(dataAuto.getNameModel()))) {
                return true;
            }
        }

        for (String model : hyundaiModelList) {
            if ((model.equals(dataAuto.getNameModel()))) {
                return true;
            }
        }

        for (String model : mercedesBenzModelList) {
            if ((model.equals(dataAuto.getNameModel()))) {
                return true;
            }
        }

        for (String model : bmwModelList) {
            if ((model.equals(dataAuto.getNameModel()))) {
                return true;
            }
        }

        for (String model : kiaModelList) {
            if ((model.equals(dataAuto.getNameModel()))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkDataAutoAdsByYear(DataAuto dataAuto) {
        for (String year : yearList) {
            if ((year.equals(dataAuto.getYear()))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkDataAutoAdsByColor(DataAuto dataAuto) {
        Color[] colors = Color.values();
        for (int i = 0; i < Color.values().length; i++) {
            if (!(colors[i].name().equals(dataAuto.getColor()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDataAutoAdsByMotorType(DataAuto dataAuto) {
        Fuel[] fuels = Fuel.values();
        for (int i = 0; i < Fuel.values().length; i++) {
            if (!(fuels[i].name().equals(dataAuto.getMotorType()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDataAutoAdsByVolume(DataAuto dataAuto) {
        for (Double volume : volumeList) {
            if ((volume.equals(dataAuto.getVolume()))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkDataAutoAdsByDrive(DataAuto dataAuto) {
        Drive[] drives = Drive.values();
        for (int i = 0; i < Drive.values().length; i++) {
            if (!(drives[i].name().equals(dataAuto.getDriveType()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDataAutoAdsByTransmission(DataAuto dataAuto) {
        Transmission[] transmissions = Transmission.values();
        for (int i = 0; i < Transmission.values().length; i++) {
            if (!(transmissions[i].name().equals(dataAuto.getTransmissionType()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDataAutoAdsByBodyStyle(DataAuto dataAuto) {
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

    @Override
    public boolean DateValidator(String date){
        Pattern pattern;
        Matcher matcher;

        if(date != null){
            pattern = Pattern.compile(DATE_PATTERN);
            matcher = pattern.matcher(date);

            return matcher.matches();
        }else{
            return true;
        }
    }

}

