package ru.ncedu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataAuto {

    private Long id;

    private Long idPicture;

    private byte[] raster;

    @Size(min = 1, max = 128)
    @Email(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
    private String email;

    @Pattern(regexp = "^(\\+7)\\([0-9]{3}\\)+\\-[0-9]{3}\\-[0-9]{2}-[0-9]{2}$", message = "ex: +7(000)-000-00-00")
    private String phone;

    @Size(min = 1, max = 128)
    private String nameBrand;

    @Size(min = 1, max = 128)
    private String nameModel;

    @Pattern(regexp = "[0-9]{4}", message = "No more than 4 digits")
    private String year;

    @Size(min = 1, max = 128)
    private String color;

    @Min(1)
    @Max(100000000)
    private double price;

    @Size(min = 1, max = 128)
    private String motorType;

    private double volume;

    @Size(min = 1, max = 128)
    private String driveType;

    @Size(min = 1, max = 128)
    private String transmissionType;

    @Size(min = 1, max = 128)
    private String bodyStyleType;
}
