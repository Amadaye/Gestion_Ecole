package com.gestionEcole.gestionEcole.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "niveaux")
public class Niveau extends AbstractEntity{

    @Column(name = "niveauid", unique = true)
    private String niveauId;

    @Column(name = "description")
    private String description;

    @Column(name = "montantinscription")
    private Integer montantInscription;

    @Column(name = "montantmensualite")
    private Integer montantMensualite;

    @Column(name = "totalscolarite")
    private Integer  totalScolarite;


    @OneToMany(mappedBy = "niveau")
    private List<Inscription> inscriptions;

}
