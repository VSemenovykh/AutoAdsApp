package ru.ncedu.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.ncedu.model.Fuel;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "motor")
@Data
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
public class Motor implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "motor_type")
    private String motorType;

    @Column(name = "volume")
    private double volume;

    @Transient
     private Fuel fuel;
}
