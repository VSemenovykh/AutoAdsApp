package ru.ncedu.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity 
@Table(name = "motor", schema = "public")
@Data
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Motor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "motor_type")
    private String motorType;

    @Column(name = "volume")
    private double volume;

//    @ManyToOne(optional=false, cascade=CascadeType.ALL)
//    @JoinColumn(name="id_motor")
//   // @Fetch(FetchMode.JOIN)
//    private Auto auto;
  
}
