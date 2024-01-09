package com.gestionEcole.gestionEcole.validator;

import com.gestionEcole.gestionEcole.dto.EleveDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EleveValidator {

    public static List<String> validate(EleveDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("Veuillez renseigner le nom del'eleve");
            errors.add("Veuillez renseigner le prenom de l'eleve");
            errors.add("Veuillez renseigner le matricule de l'eleve");
            errors.add("Veuillez renseigner la date de naissance de l'eleve");
            errors.add("Veuillez renseigner le lieu de naissance de l'eleve");
            return errors;
        }

        if (!StringUtils.hasLength(dto.getNom())) {
            errors.add("Veuillez renseigner le nom del'eleve");
        }
        if (!StringUtils.hasLength(dto.getPrenom())) {
            errors.add("Veuillez renseigner le prenom de l'eleve");
        }
        if (!StringUtils.hasLength(dto.getMatricule())) {
            errors.add("Veuillez renseigner le matricule de l'eleve");
        }
        if (dto.getDatenaissance() == null){
            errors.add("Veuillez renseigner la date de naissance de l'eleve");
        }
        if (!StringUtils.hasLength(dto.getLieunaissance())) {
            errors.add("Veuillez renseigner le lieu de naissance de l'eleve");
        }
        return errors;
    }
}
