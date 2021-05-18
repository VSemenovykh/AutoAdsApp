package ru.ncedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "change_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeHistoryAutoAds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_auto")
    private Long idAuto;

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(name = "id_username")
    private String username;

    @NotBlank
    @Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)/ ([\\d]{1,2}\\:+[\\d]{1,2}\\:+[\\d]{1,2})")
    @Column(name = "change_data")
    private String modifyData;
}
