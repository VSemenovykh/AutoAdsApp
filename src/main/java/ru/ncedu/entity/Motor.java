package ru.ncedu.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "motor")
@Data
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
public class Motor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Size(min = 1, max = 16)
    @Column(name = "motor_type")
    private String motorType;

    @NonNull
    @Min(0)
    @Column(name = "volume")
    private double volume;

    /** 1 variant **/
    @OneToMany(targetEntity = Auto.class,  fetch = FetchType.LAZY, mappedBy = "motor", orphanRemoval = false) //orphanRemoval = false , fetch = FetchType.LAZY
    private List<Auto> auto;

//    /** 2 variant**/
//    @ManyToMany
//    @JoinTable (name="auto",
//            joinColumns=@JoinColumn (name="id_motor")
//            ,inverseJoinColumns=@JoinColumn(name="id_brand")
//            )
//    private List<Brand> brand;
}
