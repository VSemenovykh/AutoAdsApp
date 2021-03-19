package ru.ncedu.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "brand")
@Data
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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

    /** 1 variant **/
    @OneToMany(targetEntity = Auto.class,  fetch = FetchType.LAZY, mappedBy = "brand", orphanRemoval = false ) //, fetch = FetchType.LAZY
    private List<Auto> Auto;

    /** 2 variant**/
//    @ManyToMany
//    @JoinTable (name="auto",
//            joinColumns=@JoinColumn (name="id_brand")
//            ,inverseJoinColumns=@JoinColumn(name="id_motor")
//            )
//    private List<Motor> motor;
}
