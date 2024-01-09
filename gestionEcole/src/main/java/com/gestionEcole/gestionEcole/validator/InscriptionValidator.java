package com.gestionEcole.gestionEcole.validator;

import com.gestionEcole.gestionEcole.dto.EleveDto;
import com.gestionEcole.gestionEcole.dto.InscriptionDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class InscriptionValidator {


    public static List<String> validate(InscriptionDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("Veuillez renseigner l'eleve");
            errors.add("Veuillez renseigner le niveau de  l'eleve");
            errors.add("Veuillez renseigner l'année scolaire ");
            errors.add("Veuillez renseigner le montant  payé");
            errors.add("Veuillez renseigner la date de paiement ");
            return errors;
        }
        if (dto.getEleve() == null || dto.getEleve().getId() == null) {
            errors.add("Veuillez renseigner l'eleve'");
        }
        if (dto.getNiveau() == null || dto.getNiveau().getId() == null) {
            errors.add("Veuillez renseigner le niveau de  l'eleve");
        }
        if (!StringUtils.hasLength(dto.getAnneeScolaire())) {
            errors.add("Veuillez renseigner l'année scolaire ");
        }
        if (dto.getMontantPaye() <= 0 ) {
            errors.add("Veuillez renseigner le montant  payé ");
        }
        if (dto.getDateInscription()  == null ){
            errors.add("Veuillez renseigner la date de paiement ");
        }

        return errors;
    }
}
