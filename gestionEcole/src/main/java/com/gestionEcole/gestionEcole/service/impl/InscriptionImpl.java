package com.gestionEcole.gestionEcole.service.impl;

import com.gestionEcole.gestionEcole.dto.InscriptionDto;
import com.gestionEcole.gestionEcole.exception.EntityNotFoundException;
import com.gestionEcole.gestionEcole.exception.ErrorCodes;
import com.gestionEcole.gestionEcole.exception.InvalidEntityException;
import com.gestionEcole.gestionEcole.model.Inscription;
import com.gestionEcole.gestionEcole.repository.InscriptionRepository;
import com.gestionEcole.gestionEcole.service.InscriptionService;
import com.gestionEcole.gestionEcole.validator.InscriptionValidator;
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
public class InscriptionImpl implements InscriptionService {

    InscriptionRepository inscriptionRepository;

    @Autowired
    public InscriptionImpl(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    @Override
    public List<InscriptionDto> getInscriptions() {
        return inscriptionRepository.findAll().stream()
                .map(InscriptionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public InscriptionDto getInscription(Integer id) {
        if (id == null) {
            //log.error("Client ID is null");
            return null;
        }
        return inscriptionRepository.findById(id)
                .map(InscriptionDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun niveau avec l'ID  " + id + " n'a pas été trouve dans la BDD",
                        ErrorCodes.INSCRIPTION_NOT_FOUND)
                );

    }

    @Override
    public InscriptionDto createInscription(InscriptionDto inscription) {
        List<String> errors = InscriptionValidator.validate(inscription);
        if (!errors.isEmpty()) {
            //log.error("Client is not valid {}", dto);
            throw new InvalidEntityException("L'inscription  n'est pas valide", ErrorCodes.INSCRIPTION_NOT_VALID, errors);
        }

        return InscriptionDto.fromEntity(
                inscriptionRepository.save(
                        InscriptionDto.toEntity(inscription)
                )
        );
    }

    @Override
    public InscriptionDto updateInscription(Integer id, Map<String, Object> fields) {
        Optional<Inscription> existingInscription = inscriptionRepository.findById(id);

        if (existingInscription.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Inscription.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    if (field.getAnnotatedType().getType().equals(LocalDate.class)) {
                        ReflectionUtils.setField(field, existingInscription.get(), LocalDate.parse((CharSequence) value, DateTimeFormatter.ISO_LOCAL_DATE));
                    } else {
                        ReflectionUtils.setField(field, existingInscription.get(), value);
                    }
                }
            });
            return InscriptionDto.fromEntity(inscriptionRepository.save(existingInscription.get()));
        }

        return null;
    }

    @Override
    public void deleteInscription(Integer id) {
        if (id == null) {
            //log.error("Client ID is null");
            return;
        }
        inscriptionRepository.deleteById(id);
    }


    @Override
    public List<InscriptionDto> getInscriptionByMatricule(String matricule) {
        List<InscriptionDto> inscriptionDto = inscriptionRepository.findAllByMatricule(matricule).stream()
                .map(InscriptionDto::fromEntity)
                .collect(Collectors.toList());
        if(matricule == null || inscriptionDto.size() == 0) {
            throw  new EntityNotFoundException(
                    "Aucune inscription d'un eleve avec ce matricule = " + matricule + " n'a ete trouve dans la BDD'",
                    ErrorCodes.ELEVE_NOT_FOUND);

        }else{
            return inscriptionDto;
        }

    }

    @Override
    public List<InscriptionDto> getInscriptionByNiveauId(String niveauId) {
        List<InscriptionDto> inscriptionDto = inscriptionRepository.findAllInscriptionByIdNiveau(niveauId).stream()
                .map(InscriptionDto::fromEntity)
                .collect(Collectors.toList());
        if(niveauId == null || inscriptionDto.size() == 0) {
            throw  new EntityNotFoundException(
                    "Aucun niveau avec cet id  " + niveauId + " ne possede des inscriptions",
                    ErrorCodes.NIVEAU_NOT_FOUND);

        }else{
            return inscriptionDto;
        }
    }


    @Override
    public List<InscriptionDto> getInscriptionByNiveauNiveauId(Integer niveauId) {
        List<InscriptionDto> inscriptionDto = inscriptionRepository.findAllInscriptionByNiveauId(niveauId).stream()
                .map(InscriptionDto::fromEntity)
                .collect(Collectors.toList());
        if(niveauId == null || inscriptionDto.size() == 0) {
            throw  new EntityNotFoundException(
                    "Aucune inscription avec ce niveau id  " + niveauId + " n'a ete trouve dans la BDD",
                    ErrorCodes.NIVEAU_NOT_FOUND);

        }else{
            return inscriptionDto;
        }
    }

    @Override
    public List<InscriptionDto> getInscriptionByEleveId(Integer id) {
        List<InscriptionDto> inscriptionDto = inscriptionRepository.findAllEleveById(id).stream()
                .map(InscriptionDto::fromEntity)
                .collect(Collectors.toList());
        if(id == null || inscriptionDto.size() == 0) {
            throw  new EntityNotFoundException(
                    "Aucun eleve avec cet id  " + id + " n'a fait une inscription",
                    ErrorCodes.ELEVE_NOT_FOUND);

        }else{
            return inscriptionDto;
        }
    }

}
