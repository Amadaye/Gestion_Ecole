package com.gestionEcole.gestionEcole.service;

import com.gestionEcole.gestionEcole.dto.NiveauDto;

import java.util.List;
import java.util.Map;


public interface NiveauService {
    List<NiveauDto> getAllNiveaux();
    NiveauDto getNiveau(Integer id );
    NiveauDto createNiveau(NiveauDto niveau);
   NiveauDto updateNiveau( Integer id, Map<String, Object> fields);
    void deleteNiveau(Integer id);
    NiveauDto getByNiveauId(String niveauId);


}
