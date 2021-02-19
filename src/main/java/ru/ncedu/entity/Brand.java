package ru.ncedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_brand")
    private String nameBrand;

    @Column(name = "name_model")
    private String nameModel;

    @Column(name = "year")
    private int year;

}
