package ru.ncedu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.ncedu.entity.Brand;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auto {

    private Long id;

    private Long idBrand;

    private String color;

    private double price;

    private Long idMotor;

    private String transmissionType;

    private String driveType;

    private String bodyStyleType;
}
