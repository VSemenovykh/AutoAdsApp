package ru.ncedu.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "auto")
@Data
@NoArgsConstructor
public class Auto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_image")
    private Long idImage;

    @NonNull
    @Min(1)
    @Column(name = "id_brand")
    private Long idBrand;

    @Min(1)
    @Column(name = "id_contact")
    private Long idContact;

    @NonNull
    @Min(1)
    @Column(name = "id_motor")
    private Long idMotor;

    @NonNull
    @Size(min = 3, max = 32)
    @Column(name = "color")
    private String color;

    @NonNull
    @Min(0)
    @Column(name = "price")
    private double price;

    @NonNull
    @Size(min = 2, max = 32)
    @Column(name = "drive_type")
    private String driveType;

    @NonNull
    @Size(min = 2, max = 32)
    @Column(name = "transmission_type")
    private String transmissionType;

    @NonNull
    @Size(min = 2, max = 32)
    @Column(name = "body_style")
    private String bodyStyleType;

    @Column(name = "adding_date")
    private String addingDate;

    @ManyToOne()
    @JoinColumn(name = "id_brand", insertable = false, updatable = false)
    private Brand brand;

    @ManyToOne()
    @JoinColumn(name = "id_motor", insertable = false, updatable = false)
    private Motor motor;

    @ManyToOne()
    @JoinColumn(name = "id_image", insertable = false, updatable = false)
    private PictureAuto pictureAuto;

    @ManyToOne()
    @JoinColumn(name = "id_contact", insertable = false, updatable = false)
    private Contact contact;

    @ManyToOne()
    @JoinColumn(name="id_user", insertable = false, updatable = false)
    private User user;

    public Auto(Long id, Long idUser, Long idImage, Long idBrand, Long idContact, Long idMotor,
                String color, double price, String transmissionType, String driveType,
                String bodyStyleType, String addingDate) {
        this.id = id;
        this.idUser = idUser;
        this.idImage = idImage;
        this.idBrand = idBrand;
        this.idContact = idContact;
        this.idMotor = idMotor;
        this.color = color;
        this.price = price;
        this.driveType = driveType;
        this.transmissionType = transmissionType;
        this.bodyStyleType = bodyStyleType;
        this.addingDate = addingDate;
    }
}
