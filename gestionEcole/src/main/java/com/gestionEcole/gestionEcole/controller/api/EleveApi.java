package com.gestionEcole.gestionEcole.controller.api;

import com.gestionEcole.gestionEcole.dto.EleveDto;
import com.gestionEcole.gestionEcole.dto.NiveauDto;
import com.gestionEcole.gestionEcole.exception.EntityNotFoundException;
import com.gestionEcole.gestionEcole.model.Eleve;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.gestionEcole.gestionEcole.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/eleves")
public interface EleveApi {

    @GetMapping(value = APP_ROOT + "/eleve/eleves", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des eleves", notes = "Cette methode permet de chercher et renvoyer la liste des eleves qui existent "
            + "dans la BDD", responseContainer = "List<EleveDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des eleves / Une liste vide")
    })
    List<EleveDto> findAll();
    @GetMapping(value = APP_ROOT + "/eleve/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un eleve par ID", notes = "Cette methode permet de chercher un eleve par son ID", response = EleveDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'eleve a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun eleve n'existe dans la BDD avec l'ID fourni")
    })
    EleveDto findById(@PathVariable("id") Integer id);

    @PostMapping(value = APP_ROOT + "/eleve/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un eleve", notes = "Cette methode permet d'enregistrer ou modifier un eleve", response = EleveDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet niveau cree / eleve"),
            @ApiResponse(code = 400, message = "L'objet eleve n'est pas valide")
    })
    EleveDto save(@RequestBody EleveDto eleve);

    @PatchMapping(value = APP_ROOT + "/eleve/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un eleve", notes = "Cette methode permet de modifier un niveau", response = EleveDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet eleve modifié / niveau"),
            @ApiResponse(code = 400, message = "L'objet eleve n'est pas valide")
    })
    EleveDto save(@PathVariable("id") Integer id,@RequestBody Map<String, Object> fields);

    @DeleteMapping(value = APP_ROOT + "/eleve/delete/{id}")
    @ApiOperation(value = "Supprimer un eleve", notes = "Cette methode permet de supprimer un eleve par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le eleve a ete supprimé")
    })
    void delete(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/eleveByMatricule/{matricule}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un eleve par code", notes = "Cette methode permet de chercher un eleve par son code ", response = EleveDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le niveau a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun niveau n'existe dans la BDD avec le code fourni")
    })
    EleveDto findEleveByMatricule(@PathVariable("matricule") String matricule);
}
