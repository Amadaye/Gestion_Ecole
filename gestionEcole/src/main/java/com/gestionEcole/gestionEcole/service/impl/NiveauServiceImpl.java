package com.gestionEcole.gestionEcole.service.impl;


import com.gestionEcole.gestionEcole.dto.NiveauDto;
import com.gestionEcole.gestionEcole.exception.*;
import com.gestionEcole.gestionEcole.model.Inscription;
import com.gestionEcole.gestionEcole.model.Niveau;
import com.gestionEcole.gestionEcole.repository.InscriptionRepository;
import com.gestionEcole.gestionEcole.repository.NiveauRepo;
import com.gestionEcole.gestionEcole.service.NiveauService;
import com.gestionEcole.gestionEcole.validator.NiveauValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NiveauServiceImpl implements NiveauService {


    NiveauRepo niveauRepo;
    InscriptionRepository inscriptionRepository;

    @Autowired
    public NiveauServiceImpl(NiveauRepo niveauRepo, InscriptionRepository inscriptionRepository) {
        this.niveauRepo = niveauRepo;
        this.inscriptionRepository = inscriptionRepository;
    }

    @Override
    public List<NiveauDto> getAllNiveaux() {
        return niveauRepo.findAll().stream()
                .map(NiveauDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public NiveauDto getNiveau(Integer id) {

        if (id == null) {
            //log.error("Client ID is null");
            return null;
        }
        return niveauRepo.findById(id)
                .map(NiveauDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun niveau avec l'ID = " + id + " n'a pas été trouve dans la BDD",
                        ErrorCodes.NIVEAU_NOT_FOUND)
                );
    }

    @Override
    public NiveauDto createNiveau(NiveauDto niveau) {

        List<String> errors = NiveauValidator.validate(niveau);
        if (!errors.isEmpty()) {
            //log.error("Client is not valid {}", dto);
            throw new InvalidEntityException("Le niveau  n'est pas valide", ErrorCodes.NIVEAU_NOT_VALID, errors);
        }

        return NiveauDto.fromEntity(
                niveauRepo.save(
                        NiveauDto.toEntity(niveau)
                )
        );
    }

    public NiveauDto updateNiveau(Integer id,Map<String, Object> fields) {
        Optional<Niveau> existingNiveau = niveauRepo.findById(id);

        if (existingNiveau.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Niveau.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    if (field.getAnnotatedType().getType().equals(LocalDate.class)) {
                        ReflectionUtils.setField(field, existingNiveau.get(), LocalDate.parse((CharSequence) value, DateTimeFormatter.ISO_LOCAL_DATE));
                    } else {
                        ReflectionUtils.setField(field, existingNiveau.get(), value);
                    }
                }
            });
            return NiveauDto.fromEntity(niveauRepo.save(existingNiveau.get()));
        }

        return null;
    }

    @Override
    public void deleteNiveau(Integer id) {
        //niveauRepo.deleteById(id);
        if (id == null) {
            //log.error("Client ID is null");
            return;
        }
        List<Inscription> inscriptions = inscriptionRepository.findAllInscriptionByNiveauId(id);
        if (!inscriptions.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un niveau qui a deja des inscriptions",
                    ErrorCodes.NIVEAU_ALREADY_IN_USE);
        }
        niveauRepo.deleteById(id);
    }

    @Override
    public NiveauDto getByNiveauId(String niveauId) {
        if (niveauId == null) {
            //log.error("Client ID is null");
            return null;
        }
        return niveauRepo.findNiveauByNiveauId(niveauId)
                .map(NiveauDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun niveau avec cet id = " + niveauId + " n'a pas été trouvé dans la BDD",
                        ErrorCodes.NIVEAU_NOT_FOUND)
                );

    }
}
