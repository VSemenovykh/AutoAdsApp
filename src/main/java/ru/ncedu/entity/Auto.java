package ru.ncedu.entity;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "auto")
@Data
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
public class Auto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Min(0)
    @Column(name = "id_brand",  nullable = false)
    private Long idBrand;

    @NonNull
    @Size(min = 3, max = 32)
    @Column(name = "color")
    private String color;

    @NonNull
    @Min(0)
    @Column(name = "price")
    private double price;

    @NonNull
    @Min(0)
    @Column(name = "id_motor",  nullable = false)
    private Long idMotor;

    @NonNull
    @Size(min = 2, max = 32)
    @Column(name = "transmission_type")
    private String transmissionType;

    @NonNull
    @Size(min = 2, max = 32)
    @Column(name = "drive_type")
    private String driveType;

    @NonNull
    @Size(min = 2, max = 32)
    @Column(name = "body_style")
    private String bodyStyleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_brand", referencedColumnName="id", insertable = false, updatable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "id_motor", referencedColumnName="id", insertable = false, updatable = false)
    private Motor motor; ;
}
