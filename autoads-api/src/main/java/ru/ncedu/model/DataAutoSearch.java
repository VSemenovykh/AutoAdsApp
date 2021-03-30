package ru.ncedu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataAutoSearch {

    private Long id;

    private byte[] raster;

    private String nameBrand;

    private String nameModel;

    private String startYear;

    private String endYear;

    private String color;

    private Double startPrice;

    private Double endPrice;

    private String motorType;

    private Double startVolume;

    private Double endVolume;

    private String driveType;

    private String transmissionType;

    private String bodyStyleType;
}
