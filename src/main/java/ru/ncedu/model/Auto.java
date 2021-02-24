package ru.ncedu.model;	

import lombok.AllArgsConstructor;	
import lombok.Data;	
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data	
@AllArgsConstructor	
@NoArgsConstructor	
public class Auto {
    private int id;	
    private Brand brand;	
    private String color;	
    private double price;	
    private Motor motor;	
    private Transmission transmissionType;	
    private Drive driveType;	
    private BodyStyle bodyStyleType;	
}