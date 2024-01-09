package com.gestionEcole.gestionEcole.controller;

import com.gestionEcole.gestionEcole.controller.api.EleveApi;
import com.gestionEcole.gestionEcole.dto.EleveDto;
import com.gestionEcole.gestionEcole.exception.EntityNotFoundException;
import com.gestionEcole.gestionEcole.model.Eleve;
import com.gestionEcole.gestionEcole.service.EleveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EleveController implements EleveApi {

    EleveService eleveService;
    @Autowired
    public EleveController(EleveService eleveService) {
        this.eleveService = eleveService;
    }

    @Override
    public List<EleveDto> findAll() {
        return eleveService.getEleves();
    }

    @Override
    public EleveDto findById(Integer id) {
        return eleveService.getEleve(id);
    }

    @Override
    public EleveDto save(EleveDto eleve) {
        return eleveService.createEleve(eleve);
    }

    @Override
    public EleveDto save(Integer id,Map<String, Object> fields) {
        return eleveService.updateEleve(id,fields);
    }

    @Override
    public void delete(Integer id) {
        eleveService.deleteEleve(id);
    }

    @Override
    public EleveDto findEleveByMatricule(String matricule) {
        return eleveService.findByMatricule(matricule);
    }
}
