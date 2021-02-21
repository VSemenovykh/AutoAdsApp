package ru.ncedu.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ncedu.model.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auto")
public class Auto {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_id")
    private Long idBrand;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private double price;

    @Column(name = "motor_id")
    private String idMotor;

    @Column(name = "transmission_type")
    private String transmissionType;

    @Column(name = "drive_type")
    private String driveType;

    @Column(name = "body_style")
    private String bodyStyleType;
}
