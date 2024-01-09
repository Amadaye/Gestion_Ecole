package com.gestionEcole.gestionEcole.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestionEcole.gestionEcole.model.Eleve;
import com.gestionEcole.gestionEcole.model.Inscription;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class EleveDto {

    private Integer id;

    private String matricule;

    private String nom;

    private String prenom;

    private LocalDate datenaissance;

    private String lieunaissance;

    private String nni;

    @JsonIgnore
    private List<Inscription> inscriptions;

    public static EleveDto fromEntity(Eleve eleve) {
        if (eleve == null) {
            return null;
        }
        return EleveDto.builder()
                .id(eleve.getId())
                .nom(eleve.getNom())
                .prenom(eleve.getPrenom())
                .matricule(eleve.getMatricule())
                .datenaissance(eleve.getDatenaissance())
                .lieunaissance(eleve.getLieunaissance())
                .nni(eleve.getNni())
                .build();
    }

    public static Eleve toEntity(EleveDto dto) {
        if (dto == null) {
            return null;
        }
        Eleve eleve = new Eleve();
        eleve.setId(dto.getId());
        eleve.setNom(dto.getNom());
        eleve.setPrenom(dto.getPrenom());
        eleve.setMatricule(dto.getMatricule());
        eleve.setDatenaissance(dto.getDatenaissance());
        eleve.setLieunaissance(dto.getLieunaissance());
        eleve.setNni(dto.getNni());
        return eleve;
    }

}
