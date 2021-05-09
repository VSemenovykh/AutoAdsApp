package ru.ncedu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSearchAuto {

    private Long id;

    private byte[] raster;

    private List<String> nameBrand;

    private List<String> nameModel;

    @Pattern(regexp = "[0-9]{4}", message = "No more than 4 digits")
    private String startYear;

    @Pattern(regexp = "[0-9]{4}", message = "No more than 4 digits")
    private String endYear;

    private List<String> color;

    @Min(1)
    private Double startPrice;

    @Max(100000000)
    private Double endPrice;

    private List<String> motorType;

    private Double startVolume;

    private Double endVolume;

    private List<String> driveType;

    private List<String> transmissionType;

    private List<String> bodyStyleType;
}
