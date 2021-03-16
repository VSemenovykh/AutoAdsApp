package ru.ncedu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoJoin {

    private Long id;

    private String nameBrand;

    private String nameModel;

    private String year;

    private String color;

    private double price;

    private String motorType;

    private double volume;

    private String driveType;

    private String transmissionType;

    private String bodyStyleType;
  
    private String bodyStyle;
}
