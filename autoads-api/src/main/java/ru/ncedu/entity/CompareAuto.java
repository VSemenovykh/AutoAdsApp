package ru.ncedu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "compare_auto")
@Data
@NoArgsConstructor
public class CompareAuto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_auto")
    private Long idAuto;

    @Column(name = "id_user")
    private Long idUser;

    public CompareAuto(Long id, Long idAuto, Long idUser){
        this.id = id;
        this.idAuto = idAuto;
        this.idUser = idUser;
    }
}
