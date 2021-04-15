package ru.ncedu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSearchAuto {

    private Long id;

    private byte[] raster;

    private List<String> nameBrand;

    private List<String> nameModel;

    private String startYear;

    private String endYear;

    private List<String> color;

    private Double startPrice;

    private Double endPrice;

    private List<String> motorType;

    private Double startVolume;

    private Double endVolume;

    private List<String> driveType;

    private List<String> transmissionType;

    private List<String> bodyStyleType;
}
