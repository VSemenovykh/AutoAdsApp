package ru.ncedu.implement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.ncedu.model.*;
import ru.ncedu.services.ValidDataSearchAutoAds;

import java.util.List;

@Service
public class ValidDataSearchAutoAdsImpl implements ValidDataSearchAutoAds {

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

    @Override
    public boolean checkDataSearchAutoAds(DataSearchAuto dataSearchAuto) {
        if (checkDataSearchAutoAdsByNameBrand(dataSearchAuto) && checkDataSearchAutoAdsByNameModel(dataSearchAuto) && checkDataSearchAutoAdsByStartYear(dataSearchAuto) &&
                checkDataSearchAutoAdsByEndYear(dataSearchAuto) && checkDataSearchAutoAdsByColor(dataSearchAuto) && checkDataSearchAutoAdsByMotorType(dataSearchAuto) &&
                checkDataSearchAutoAdsByStartVolume(dataSearchAuto) && checkDataSearchAutoAdsByEndVolume(dataSearchAuto) && checkDataSearchAutoAdsByDrive(dataSearchAuto) &&
                checkDataSearchAutoAdsByTransmission(dataSearchAuto) && checkDataSearchAutoAdsByBodyStyle(dataSearchAuto)) {

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkDataSearchAutoAdsByNameBrand(DataSearchAuto dataSearchAuto) {
        if ((dataSearchAuto.getNameBrand() != null) && (!dataSearchAuto.getNameBrand().isEmpty())) {
            for (String brand : dataSearchAuto.getNameBrand()) {
                for (String brandList : nameBrandList) {
                    if (brand.equals(brandList)) {
                        return true;
                    }
                }
            }

            return false;
        }

        return true;
    }

    @Override
    public boolean checkDataSearchAutoAdsByNameModel(DataSearchAuto dataSearchAuto) {
        if ((dataSearchAuto.getNameModel() != null) && (!dataSearchAuto.getNameModel().isEmpty())) {
            for (String model : dataSearchAuto.getNameModel()) {
                for (String modelList : audiModelList) {
                    if (model.equals(modelList)) {
                        return true;
                    }
                }

                for (String modelList : fordModelList) {
                    if (model.equals(modelList)) {
                        return true;
                    }
                }

                for (String modelList : hondaModelList) {
                    if (model.equals(modelList)) {
                        return true;
                    }
                }

                for (String modelList : hyundaiModelList) {
                    if (model.equals(modelList)) {
                        return true;
                    }
                }

                for (String modelList : mercedesBenzModelList) {
                    if (model.equals(modelList)) {
                        return true;
                    }
                }

                for (String modelList : bmwModelList) {
                    if (model.equals(modelList)) {
                        return true;
                    }
                }

                for (String modelList : kiaModelList) {
                    if (model.equals(modelList)) {
                        return true;
                    }
                }

            }
            return false;
        }

        return true;
    }

    @Override
    public boolean checkDataSearchAutoAdsByStartYear(DataSearchAuto dataSearchAuto) {
        if (dataSearchAuto.getStartYear() != null) {
            for (String year : yearList) {
                if ((dataSearchAuto.getStartYear().equals(year))) {
                    return true;
                }
            }
            return false;
        }

        return true;
    }

    @Override
    public boolean checkDataSearchAutoAdsByEndYear(DataSearchAuto dataSearchAuto) {
        if (dataSearchAuto.getEndYear() != null) {
            for (String year : yearList) {
                if ((dataSearchAuto.getEndYear().equals(year))) {
                    return true;
                }
            }
            return false;
        }

        return true;
    }

    @Override
    public boolean checkDataSearchAutoAdsByColor(DataSearchAuto dataSearchAuto) {
        Color[] colors = Color.values();
        if ((dataSearchAuto.getColor() != null) && (!dataSearchAuto.getColor().isEmpty())) {
            for (String color : dataSearchAuto.getColor()) {
                for (Color colorEnum : colors) {
                    if (color.equals(colorEnum.name())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean checkDataSearchAutoAdsByMotorType(DataSearchAuto dataSearchAuto) {
        Fuel[] fuels = Fuel.values();
        if ((dataSearchAuto.getMotorType() != null) && (!dataSearchAuto.getMotorType().isEmpty())) {
            for (String motorType : dataSearchAuto.getMotorType()) {
                for (Fuel fuel : fuels) {
                    if (motorType.equals(fuel.name())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean checkDataSearchAutoAdsByStartVolume(DataSearchAuto dataSearchAuto) {
        if (dataSearchAuto.getStartVolume() != null) {
            for (Double volume : volumeList) {
                if ((dataSearchAuto.getStartVolume().equals(volume))) {
                    return true;
                }
            }
            return false;
        }

        return true;
    }

    @Override
    public boolean checkDataSearchAutoAdsByEndVolume(DataSearchAuto dataSearchAuto) {
        if (dataSearchAuto.getEndVolume() != null) {
            for (Double volume : volumeList) {
                if ((dataSearchAuto.getEndVolume().equals(volume))) {
                    return true;
                }
            }
            return false;
        }

        return true;
    }

    @Override
    public boolean checkDataSearchAutoAdsByDrive(DataSearchAuto dataSearchAuto) {
        Drive[] drives = Drive.values();
        if ((dataSearchAuto.getDriveType() != null) && (!dataSearchAuto.getDriveType().isEmpty())) {
            for (String drive : dataSearchAuto.getDriveType()) {
                for (Drive driveEnum : drives) {
                    if (drive.equals(driveEnum.name())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean checkDataSearchAutoAdsByTransmission(DataSearchAuto dataSearchAuto) {
        Transmission[] transmissions = Transmission.values();
        if ((dataSearchAuto.getTransmissionType() != null) && (!dataSearchAuto.getTransmissionType().isEmpty())) {
            for (String transmission : dataSearchAuto.getTransmissionType()) {
                for (Transmission transmissionEnum : transmissions) {
                    if (transmission.equals(transmissionEnum.name())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean checkDataSearchAutoAdsByBodyStyle(DataSearchAuto dataSearchAuto) {
        BodyStyle[] bodyStyles = BodyStyle.values();
        if ((dataSearchAuto.getBodyStyleType() != null) && (!dataSearchAuto.getBodyStyleType().isEmpty())) {
            for (String bodyStyle : dataSearchAuto.getBodyStyleType()) {
                for (BodyStyle bodyStyleEnum : bodyStyles) {
                    if (bodyStyle.equals(bodyStyleEnum.name())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }
}
