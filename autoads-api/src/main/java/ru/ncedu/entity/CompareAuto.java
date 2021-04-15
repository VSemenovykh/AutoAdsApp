package ru.ncedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "compare_auto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompareAuto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_auto")
    private Long idAuto;
}
