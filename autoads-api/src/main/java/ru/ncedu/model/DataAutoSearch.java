package ru.ncedu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataAutoSearch {

    private Long id;

    private byte[] raster = null;

    private String nameBrand = null;

    private String nameModel = null;

    private String startYear = null;

    private String endYear = null;

    private String color = null;

    private Double startPrice = null;

    private Double endPrice = null;

    private String motorType = null;

    private Double startVolume = null;

    private Double endVolume = null;

    private String driveType = null;

    private String transmissionType = null;

    private String bodyStyleType = null ;
}
