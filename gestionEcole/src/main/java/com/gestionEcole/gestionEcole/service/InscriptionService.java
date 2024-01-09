package com.gestionEcole.gestionEcole.service;

import com.gestionEcole.gestionEcole.dto.InscriptionDto;
import com.gestionEcole.gestionEcole.exception.EntityNotFoundException;
import com.gestionEcole.gestionEcole.model.Inscription;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface InscriptionService {

    List<InscriptionDto> getInscriptions();
    InscriptionDto getInscription(Integer id);
    InscriptionDto createInscription(InscriptionDto inscription);

    InscriptionDto updateInscription( Integer id, Map<String, Object> fields);
    void deleteInscription(Integer id);

    List<InscriptionDto> getInscriptionByMatricule(String matricule);

    List<InscriptionDto> getInscriptionByNiveauId(String niveauId);
    List<InscriptionDto> getInscriptionByNiveauNiveauId(Integer niveauId);

    List<InscriptionDto> getInscriptionByEleveId(Integer id);
}
