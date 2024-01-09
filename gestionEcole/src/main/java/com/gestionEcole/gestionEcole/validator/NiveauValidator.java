package com.gestionEcole.gestionEcole.validator;

import com.gestionEcole.gestionEcole.dto.EleveDto;
import com.gestionEcole.gestionEcole.dto.NiveauDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class NiveauValidator {

    public static List<String> validate(NiveauDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("Veuillez renseigner l'id du niveau");
            errors.add("Veuillez renseigner la desription du niveau");
            errors.add("Veuillez renseigner le montant de l'inscription");
            errors.add("Veuillez renseigner le montant de la mensualité");
            errors.add("Veuillez renseigner le montant total de la scolarité ");
            return errors;
        }

        if (!StringUtils.hasLength(dto.getNiveauId())) {
            errors.add("Veuillez renseigner l'id du niveau");
        }
        if (!StringUtils.hasLength(dto.getDescription())) {
            errors.add("Veuillez renseigner la desription du niveau");
        }
        if (dto.getMontantInscription() <= 0 ) {
            errors.add("Veuillez renseigner le montant de l'inscription");
        }
        if (dto.getMontantMensualite() <= 0){
            errors.add("Veuillez renseigner le montant de la mensualité");
        }
        if (dto.getTotalScolarite() <= 0 ) {
            errors.add("Veuillez renseigner le montant total de la scolarité ");
        }
        return errors;
    }
}
