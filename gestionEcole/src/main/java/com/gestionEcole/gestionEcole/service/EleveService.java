package com.gestionEcole.gestionEcole.service;

import com.gestionEcole.gestionEcole.dto.EleveDto;
import com.gestionEcole.gestionEcole.exception.EntityNotFoundException;
import com.gestionEcole.gestionEcole.model.Eleve;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EleveService {

    List<EleveDto> getEleves();
    EleveDto getEleve(Integer id);
    EleveDto createEleve(EleveDto eleve);
//    Eleve updateEleve(Eleve eleve);

    EleveDto updateEleve( Integer id, Map<String, Object> fields);

    void deleteEleve(Integer id);

    EleveDto findByMatricule(String matricule);
}
