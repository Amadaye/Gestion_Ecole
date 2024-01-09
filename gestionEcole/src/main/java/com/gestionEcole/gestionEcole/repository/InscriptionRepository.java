package com.gestionEcole.gestionEcole.repository;

import com.gestionEcole.gestionEcole.model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

   //@Query("select * from Inscription i where i.eleve.matricule =:matricule")
   @Query("SELECT i FROM Inscription i WHERE i.eleve.matricule = ?1")
    List<Inscription> findAllByMatricule(String matricule);

    @Query("SELECT ins FROM Inscription ins WHERE ins.niveau.id = ?1")
    List<Inscription> findAllInscriptionByNiveauId( Integer id);

    @Query("SELECT iniv FROM Inscription iniv WHERE iniv.niveau.niveauId = ?1")
    List<Inscription> findAllInscriptionByIdNiveau( String niveauId);

   @Query("SELECT in FROM Inscription in WHERE in.eleve.id = ?1")
    List<Inscription> findAllEleveById( Integer id);

}
