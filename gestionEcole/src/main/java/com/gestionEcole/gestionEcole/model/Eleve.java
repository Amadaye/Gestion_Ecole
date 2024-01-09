package com.gestionEcole.gestionEcole.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "eleves")
public class Eleve extends AbstractEntity{

    @Column(name = "matricule")
    private String matricule;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "datenaissance")
    private LocalDate datenaissance;

    @Column(name = "lieunaissance")
    private String lieunaissance;

    @Column(name = "nni")
    private String nni;

    @OneToMany(mappedBy = "eleve")
    private List<Inscription> inscriptions;

}
