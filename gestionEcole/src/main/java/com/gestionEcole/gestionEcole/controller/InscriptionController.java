package com.gestionEcole.gestionEcole.controller;

import com.gestionEcole.gestionEcole.controller.api.InscriptionApi;
import com.gestionEcole.gestionEcole.dto.InscriptionDto;
import com.gestionEcole.gestionEcole.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class InscriptionController implements InscriptionApi {

    InscriptionService inscriptionService;

    @Autowired
    public InscriptionController(InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @Override
    public List<InscriptionDto> findAll() {
        return inscriptionService.getInscriptions();
    }

    @Override
    public InscriptionDto findById(Integer id) {
        return inscriptionService.getInscription(id);
    }

    @Override
    public InscriptionDto save(InscriptionDto dto) {
        return inscriptionService.createInscription(dto);
    }

    @Override
    public InscriptionDto save(Integer id,Map<String, Object> fields) {
        return inscriptionService.updateInscription(id,fields);
    }

    @Override
    public void delete(Integer id) {
        inscriptionService.deleteInscription(id);
    }

    @Override
    public List<InscriptionDto> findAllByMatricule(String matricule) {
        return inscriptionService.getInscriptionByMatricule(matricule);
    }

    @Override
    public List<InscriptionDto> findAllInscriptionByNiveauId(Integer id) {
        return inscriptionService.getInscriptionByNiveauNiveauId(id);
    }

    @Override
    public List<InscriptionDto> findAllEleveById(Integer id) {
        return inscriptionService.getInscriptionByNiveauNiveauId(id);
    }


    @Override
    public List<InscriptionDto> findAllInscriptionByIdNiveau(String niveauId) {
        return inscriptionService.getInscriptionByNiveauId(niveauId);
    }

    @Override
    public List<InscriptionDto> findAllByEleveId(Integer id) {

        return inscriptionService.getInscriptionByEleveId(id);
    }
}
