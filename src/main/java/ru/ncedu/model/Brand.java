package ru.ncedu.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    private int id;

    private String brandName;

    private String modelName;

    private String year;
}
