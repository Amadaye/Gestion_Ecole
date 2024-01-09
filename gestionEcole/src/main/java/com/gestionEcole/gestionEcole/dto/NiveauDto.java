package com.gestionEcole.gestionEcole.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestionEcole.gestionEcole.model.Inscription;
import com.gestionEcole.gestionEcole.model.Niveau;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NiveauDto {


    private Integer id;

    private String niveauId;

    private String description;

    private Integer montantInscription;

    private Integer montantMensualite;

    private Integer  totalScolarite;

    @JsonIgnore
    private List<Inscription> inscriptions;

    public static NiveauDto fromEntity(Niveau niveau) {
        if (niveau == null) {
            return null;
        }
        return NiveauDto.builder()
                .id(niveau.getId())
                .niveauId(niveau.getNiveauId())
                .description(niveau.getDescription())
                .montantInscription(niveau.getMontantInscription())
                .montantMensualite(niveau.getMontantMensualite())
                .totalScolarite(niveau.getMontantMensualite())
                .build();
    }

    public static Niveau toEntity(NiveauDto dto) {
        if (dto == null) {
            return null;
        }
        Niveau niveau = new Niveau();
        niveau.setId(dto.getId());
        niveau.setNiveauId(dto.getNiveauId());
        niveau.setDescription(dto.getDescription());
        niveau.setMontantInscription(dto.getMontantInscription());
        niveau.setMontantMensualite(dto.getMontantMensualite());
        niveau.setTotalScolarite(dto.getTotalScolarite());
        return niveau;
    }
}
