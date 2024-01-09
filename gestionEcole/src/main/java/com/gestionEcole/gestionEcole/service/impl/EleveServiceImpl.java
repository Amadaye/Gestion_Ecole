package com.gestionEcole.gestionEcole.service.impl;

import com.gestionEcole.gestionEcole.dto.EleveDto;
import com.gestionEcole.gestionEcole.exception.EntityNotFoundException;
import com.gestionEcole.gestionEcole.exception.ErrorCodes;
import com.gestionEcole.gestionEcole.exception.InvalidEntityException;
import com.gestionEcole.gestionEcole.exception.InvalidOperationException;
import com.gestionEcole.gestionEcole.model.Eleve;
import com.gestionEcole.gestionEcole.model.Inscription;
import com.gestionEcole.gestionEcole.repository.EleveRepository;
import com.gestionEcole.gestionEcole.repository.InscriptionRepository;
import com.gestionEcole.gestionEcole.service.EleveService;
import com.gestionEcole.gestionEcole.validator.EleveValidator;
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
public class EleveServiceImpl implements EleveService {

    EleveRepository eleveRepository;
    InscriptionRepository inscriptionRepository;

    @Autowired
    public EleveServiceImpl(EleveRepository eleveRepository, InscriptionRepository inscriptionRepository) {
        this.eleveRepository = eleveRepository;
        this.inscriptionRepository = inscriptionRepository;
    }

    @Override
    public List<EleveDto> getEleves() {
        return eleveRepository.findAll().stream()
                .map(EleveDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public EleveDto getEleve(Integer id) {
        if (id == null) {
            //log.error("L'ID de l'eleve est null");
            return null;
        }
        return eleveRepository.findById(id)
                .map(EleveDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun eleve avec l'ID = " + id + " n'a pas été trouve dans la BDD",
                        ErrorCodes.ELEVE_NOT_FOUND)
                );
    }

    @Override
    public EleveDto createEleve(EleveDto eleve) {
        List<String> errors = EleveValidator.validate(eleve);
        if (!errors.isEmpty()) {
            //log.error("Client is not valid {}", dto);
            throw new InvalidEntityException("L'eleve  n'est pas valide", ErrorCodes.ELEVE_NOT_VALID, errors);
        }

        return EleveDto.fromEntity(
                eleveRepository.save(
                        EleveDto.toEntity(eleve)
                )
        );
    }

    @Override
    public EleveDto updateEleve(Integer id, Map<String, Object> fields) {
        Optional<Eleve> existingEleve = eleveRepository.findById(id);

        if (existingEleve.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Eleve.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    if (field.getAnnotatedType().getType().equals(LocalDate.class)) {
                        ReflectionUtils.setField(field, existingEleve.get(), LocalDate.parse((CharSequence) value, DateTimeFormatter.ISO_LOCAL_DATE));
                    } else {
                        ReflectionUtils.setField(field, existingEleve.get(), value);
                    }
                }
            });
            return EleveDto.fromEntity(eleveRepository.save(existingEleve.get()));
        }

        return null;
    }

    @Override
    public void deleteEleve(Integer id) {
        if (id == null) {
            //log.error("Client ID is null");
            return;
        }
        List<Inscription> inscriptions = inscriptionRepository.findAllEleveById(id);
        if (!inscriptions.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un eleve qui a deja des inscriptions",
                    ErrorCodes.ELEVE_ALREADY_IN_USE);
        }
        eleveRepository.deleteById(id);
    }

    @Override
    public EleveDto findByMatricule(String matricule) {
        EleveDto eleve = EleveDto.fromEntity(eleveRepository.findByMatricule(matricule));
        if(matricule == null || eleve == null) {
            throw  new EntityNotFoundException(
                    "Aucun eleve avec cet id  " + matricule + " n'a pas été trouvé dans la BDD",
                    ErrorCodes.ELEVE_NOT_FOUND);

        }else{
            return eleve;
        }
    }


}
