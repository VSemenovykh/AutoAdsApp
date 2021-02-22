package ru.ncedu.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.ncedu.entity.Auto;

@Entity
@Table(name = "brand", schema = "public")
@Data
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Brand {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_brand")
    private String nameBrand;

    @Column(name = "name_model")
    private String nameModel;

    @Column(name = "year")
    private String year;

//    @ManyToOne(optional=false, cascade=CascadeType.ALL)
//    @JoinColumn(name="id_brand")
////    @Fetch(FetchMode.JOIN)
//    private Auto auto;

}
