package com.gestionEcole.gestionEcole.controller.api;

import com.gestionEcole.gestionEcole.dto.InscriptionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.gestionEcole.gestionEcole.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/inscriptions")
public interface InscriptionApi {

    @GetMapping(value = APP_ROOT + "/inscription/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des inscriptions", notes = "Cette methode permet de chercher et renvoyer la liste des inscriptions qui existent "
            + "dans la BDD", responseContainer = "List<InscriptionDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des inscriptions / Une liste vide")
    })
    List<InscriptionDto> findAll();
    @GetMapping(value = APP_ROOT + "/inscription/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une inscription par ID", notes = "Cette methode permet de chercher une inscription par son ID", response = InscriptionDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'inscription a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune inscription n'existe dans la BDD avec l'ID fourni")
    })
    InscriptionDto findById(@PathVariable("id") Integer id);

    @PostMapping(value = APP_ROOT + "/inscription/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une inscription", notes = "Cette methode permet d'enregistrer ou modifier une inscription", response = InscriptionDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet inscription cree / inscription"),
            @ApiResponse(code = 400, message = "L'objet inscription n'est pas valide")
    })
    InscriptionDto save(@RequestBody InscriptionDto dto);

    @PatchMapping(value = APP_ROOT + "/inscriptions/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une inscription ", notes = "Cette methode permet de modifier une inscription ", response = InscriptionDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet inscription modifié / inscription"),
            @ApiResponse(code = 400, message = "L'objet inscription n'est pas valide")
    })
    InscriptionDto save(@PathVariable("id") Integer id,@RequestBody Map<String, Object> fields);
    @DeleteMapping(value = APP_ROOT + "/inscription/delete/{id}")
    @ApiOperation(value = "Supprimer une inscriptions", notes = "Cette methode permet de supprimer une inscription par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'inscription a ete supprimé")
    })
    void delete(@PathVariable("id") Integer id);
    @GetMapping(value = APP_ROOT + "/inscription/byMatricule/{matricule}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des inscriptions", notes = "Cette methode permet de chercher et renvoyer la liste des inscriptions qui existent "
            + "dans la BDD", responseContainer = "List<InscriptionDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des inscriptions / Une liste vide")
    })
    List<InscriptionDto> findAllByMatricule(@PathVariable("matricule") String matricule);

    @GetMapping(value = APP_ROOT + "/inscription/byIdNiveau/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des inscriptions d'un niveau", notes = "Cette methode permet de chercher et renvoyer la liste des inscriptions qui existent "
            + "dans la BDD", responseContainer = "List<InscriptionDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des inscriptions d'un niveau / Une liste vide")
    })

    List<InscriptionDto> findAllInscriptionByNiveauId(@PathVariable("id") Integer id);

    List<InscriptionDto> findAllEleveById(Integer id);

    @GetMapping(value = APP_ROOT + "/inscription/byNiveauId/{niveauId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des inscriptions pour un niveau", notes = "Cette methode permet de chercher et renvoyer la liste des inscriptions qui existent "
            + "dans la BDD", responseContainer = "List<InscriptionDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des inscriptions pour un niveau / Une liste vide")
    })

    List<InscriptionDto> findAllInscriptionByIdNiveau(@PathVariable("niveauId") String niveauId);
    @GetMapping(value = APP_ROOT + "/inscription/byEleveId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des inscriptions d'un eleve suivant son Id", notes = "Cette methode permet de chercher et renvoyer la liste des inscriptions qui existent "
            + "dans la BDD", responseContainer = "List<InscriptionDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des inscriptions d'un eleve/ Une liste vide")
    })
    List<InscriptionDto> findAllByEleveId(@PathVariable("id") Integer id);
}
