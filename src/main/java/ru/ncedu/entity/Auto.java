package ru.ncedu.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "auto")
@Data
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
public class Auto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_brand")
    private Long idBrand;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private double price;

    @Column(name = "id_motor")
    private Long idMotor;

    @Column(name = "transmission_type")
    private String transmissionType;

    @Column(name = "drive_type")
    private String driveType;

    @Column(name = "body_style")
    private String bodyStyleType;
}
