package com.gestionEcole.gestionEcole.repository;

import com.gestionEcole.gestionEcole.model.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EleveRepository extends JpaRepository<Eleve,Integer> {

    Eleve findByMatricule(String matricule);
}
