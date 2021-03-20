package ru.ncedu.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    private Long id;

    private String nameBrand;

    private String nameModel;

    private String year;
}
