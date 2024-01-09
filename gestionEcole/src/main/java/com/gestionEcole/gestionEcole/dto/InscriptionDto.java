package com.gestionEcole.gestionEcole.dto;

import com.gestionEcole.gestionEcole.model.Inscription;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class InscriptionDto {

    private Integer id;

    private EleveDto eleve;


    private NiveauDto niveau;

    private String anneeScolaire;

    private Integer montantPaye;

    private LocalDate dateInscription;

    public static InscriptionDto fromEntity(Inscription inscription) {
        if (inscription == null) {
            return null;
        }
        return InscriptionDto.builder()
                .id(inscription.getId())
                .eleve(EleveDto.fromEntity(inscription.getEleve()))
                .niveau(NiveauDto.fromEntity(inscription.getNiveau()))
                .anneeScolaire(inscription.getAnneeScolaire())
                .montantPaye(inscription.getMontantPaye())
                .dateInscription(inscription.getDateInscription())
                .build();
    }

    public static Inscription toEntity(InscriptionDto dto) {
        if (dto == null) {
            return null;
        }

        Inscription inscription = new Inscription();
        inscription.setId(dto.getId());
        inscription.setEleve(EleveDto.toEntity(dto.getEleve()));
        inscription.setNiveau(NiveauDto.toEntity(dto.getNiveau()));
        inscription.setAnneeScolaire(dto.getAnneeScolaire());
        inscription.setMontantPaye(dto.getMontantPaye());
        inscription.setDateInscription(dto.getDateInscription());
        return inscription;
    }
}
