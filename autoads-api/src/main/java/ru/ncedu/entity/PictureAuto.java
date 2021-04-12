package ru.ncedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "image_auto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PictureAuto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "image_name")
    private String nameImage;

    @Column(name = "raster", length = Integer.MAX_VALUE, nullable = true)
    private byte[] raster;
}
