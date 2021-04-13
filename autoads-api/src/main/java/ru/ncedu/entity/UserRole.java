package ru.ncedu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long idUser;

    @Column(name = "role_id")
    private Long idRole;

    public UserRole(Long id, Long idUser, Long idRole) {
        this.id = id;
        this.idUser = idUser;
        this.idRole = idRole;
    }
}
