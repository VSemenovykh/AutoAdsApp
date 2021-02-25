package ru.ncedu.model;	

import lombok.AllArgsConstructor;	
import lombok.Data;	
import lombok.NoArgsConstructor;	

@Data	
@AllArgsConstructor	
@NoArgsConstructor	
public class Motor{

    private int id;	

    private Fuel motorType;

    private double volume;

}