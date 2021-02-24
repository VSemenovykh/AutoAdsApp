package ru.ncedu.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.ncedu.entity.Auto;

@Entity
@Table(name = "brand", schema = "public")
@Data
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
public class Brand implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_brand")
    private String nameBrand;

    @Column(name = "name_model")
    private String nameModel;

    @Column(name = "year")
    private String year;

    @Column(name = "id_auto")
    private Long idAuto;

}
