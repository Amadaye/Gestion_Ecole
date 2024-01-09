package com.gestionEcole.gestionEcole.controller.api;

import com.gestionEcole.gestionEcole.dto.NiveauDto;
import com.gestionEcole.gestionEcole.model.Niveau;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


import static com.gestionEcole.gestionEcole.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/niveaux")
public interface NiveauApi {

    @GetMapping(value = APP_ROOT + "/niveau/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des niveaux", notes = "Cette methode permet de chercher et renvoyer la liste des niveaux qui existent "
            + "dans la BDD", responseContainer = "List<NiveauDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des niveaux / Une liste vide")
    })
    List<NiveauDto> findAll();

    @GetMapping(value = APP_ROOT + "/niveau/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un niveau par ID", notes = "Cette methode permet de chercher un niveau par son ID", response = NiveauDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le niveau a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun niveau n'existe dans la BDD avec l'ID fourni")
    })
    NiveauDto findById(@PathVariable("id") Integer id);

    @PostMapping(value = APP_ROOT + "/niveaux/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un niveau", notes = "Cette methode permet d'enregistrer ou modifier un niveau", response = NiveauDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet niveau cree / niveau"),
            @ApiResponse(code = 400, message = "L'objet niveau n'est pas valide")
    })
    NiveauDto save(@RequestBody NiveauDto dto);

    /*@PutMapping("updateNiveau/{id}")
    public Niveau updateNiveau(@PathVariable("id") Long id, @RequestBody  Niveau niveau){
        return niveauService.updateNiveau(niveau);
    }*/
    @PatchMapping(value = APP_ROOT + "/niveaux/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un niveau", notes = "Cette methode permet de modifier un niveau", response = NiveauDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet niveau modifié / niveau"),
            @ApiResponse(code = 400, message = "L'objet niveau n'est pas valide")
    })
    NiveauDto save(@PathVariable("id") Integer id,@RequestBody Map<String, Object> fields);
    @DeleteMapping(value = APP_ROOT + "/niveau/delete/{id}")
    @ApiOperation(value = "Supprimer un niveau", notes = "Cette methode permet de supprimer un niveau par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le niveau a ete supprimé")
    })
    void delete(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/niveauId/{niveauId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un niveau par code", notes = "Cette methode permet de chercher un niveau par son code ", response = NiveauDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le niveau a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun niveau n'existe dans la BDD avec le code fourni")
    })
    NiveauDto findByNiveauId(@PathVariable("niveauId") String niveauId);
}
