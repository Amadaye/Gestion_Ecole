package com.gestionEcole.gestionEcole.controller;

import com.gestionEcole.gestionEcole.controller.api.NiveauApi;
import com.gestionEcole.gestionEcole.dto.NiveauDto;
import com.gestionEcole.gestionEcole.model.Niveau;
import com.gestionEcole.gestionEcole.service.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class NiveauController implements NiveauApi {

    NiveauService niveauService;
    @Autowired
    public NiveauController(NiveauService niveauService) {
        this.niveauService = niveauService;
    }

    @Override
    public List<NiveauDto> findAll() {
        return niveauService.getAllNiveaux();
    }

    @Override
    public NiveauDto findById(Integer id) {
        return niveauService.getNiveau(id);
    }

    @Override
    public NiveauDto save(NiveauDto dto) {
        return niveauService.createNiveau(dto);
    }

    @Override
    public NiveauDto save(Integer id, Map<String, Object> fields) {
        return niveauService.updateNiveau(id,fields);
    }

    @Override
    public void delete(Integer id) {
        niveauService.deleteNiveau(id);
    }

    @Override
    public NiveauDto findByNiveauId(String niveauId) {
        return niveauService.getByNiveauId(niveauId);
    }
}
