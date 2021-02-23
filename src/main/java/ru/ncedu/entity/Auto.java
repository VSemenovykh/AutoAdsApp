package ru.ncedu.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Fetch;
import javax.persistence.FetchType;

import org.hibernate.annotations.FetchMode;
import ru.ncedu.model.BodyStyle;
import ru.ncedu.model.Drive;
import ru.ncedu.model.Transmission;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "auto",schema = "public")
@Data
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
public class Auto implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_brand")
    private Long idBrand;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private double price;

    @Column(name = "id_motor")
    private Long idMotor;

    @Column(name = "transmission_type")
    private String transmission;

    @Column(name = "drive_type")
    private String drive;

    @Column(name = "body_style")
    private String bodyStyle;

}
