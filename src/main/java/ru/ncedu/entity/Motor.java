package ru.ncedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "motor")
public class Motor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "motor_type")
    private String motorType;

    @Column(name = "volume")
    private double volume;
}
