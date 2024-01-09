package com.gestionEcole.gestionEcole.repository;

import com.gestionEcole.gestionEcole.dto.NiveauDto;
import com.gestionEcole.gestionEcole.model.Niveau;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NiveauRepo extends JpaRepository<Niveau,Integer> {
    Optional<Niveau>findNiveauByNiveauId(String niveauId);

    //@Query(value = "SELECT * FROM Niveau n WHERE n.niveauId = :niveauId", nativeQuery = true)
    //Optional<Niveau> findByNiveauId(String niveauId);
}
