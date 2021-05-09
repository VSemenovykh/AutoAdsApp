package ru.ncedu.implement;

import org.springframework.stereotype.Service;
import ru.ncedu.model.*;
import ru.ncedu.services.ValidDataSearchAutoAds;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ValidDataSearchAutoAdsImp implements ValidDataSearchAutoAds {

    @Override
    public boolean checkDataAutoAds(DataSearchAuto dataSearchAuto) {
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
        List<String> nameBrandList = new ArrayList<>(Arrays.asList("AUDI", "FORD", "HONDA", "HYUNDAI", "BMW", "MERCEDES-BENZ", "KIA"));
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
        List<String> nameModelList = new ArrayList<>(Arrays.asList("A3", "A4", "A8", "FIESTA", "FOCUS", "MONDEO", "ACCORD", "CROSSTOUR", "HONDA", "SOLARIS", "ELANTRA", "SONATA", "M8",
                                                                   "M5", "M6", "GLE AMG", "AMG GT", "MAYBACH GLS", "CERATO", "KIA", "KIA"));
        if ((dataSearchAuto.getNameModel() != null) && (!dataSearchAuto.getNameModel().isEmpty())) {
            for (String model : dataSearchAuto.getNameModel()) {
                for (String modelList : nameModelList) {
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
        List<String> yearList = new ArrayList<>(Arrays.asList("2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010",
                                                              "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"));
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
        List<String> yearList = new ArrayList<>(Arrays.asList("2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010",
                                                              "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"));
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
        List<Double> volumeList = new ArrayList<>(Arrays.asList(0.2, 0.4, 0.6, 0.8, 1.0, 1.2, 1.4, 1.6, 1.8, 2.0, 2.2, 2.4, 2.6, 2.7, 2.8, 3.0, 3.2, 4.0, 5.0, 5.5));
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
        List<Double> volumeList = new ArrayList<>(Arrays.asList(0.2, 0.4, 0.6, 0.8, 1.0, 1.2, 1.4, 1.6, 1.8, 2.0, 2.2, 2.4, 2.6, 2.7, 2.8, 3.0, 3.2, 4.0, 5.0, 5.5));
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
