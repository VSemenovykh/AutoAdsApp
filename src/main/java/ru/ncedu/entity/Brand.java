package ru.ncedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "brand")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Size(min = 1, max = 128)
    @Column(name = "name_brand")
    private String nameBrand;

    @NonNull
    @Size(min = 1, max = 128)
    @Column(name = "name_model")
    private String nameModel;

    @NonNull
    @Size(min = 4, max = 32)
    @Column(name = "year")
    private String year;
}
