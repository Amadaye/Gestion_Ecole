package com.gestionEcole.gestionEcole.utils;

public interface Constants {

    String APP_ROOT = "gestiondeecole/v1";

    String ELEVE_ENDPOINT = APP_ROOT + "/eleves";
    String CREATE_ELEVE_ENDPOINT = ELEVE_ENDPOINT + "/create";
    String FIND_ELEVE_BY_ID_ENDPOINT = ELEVE_ENDPOINT + "/{idEleve}";
    String FIND_ELEVE_BY_MATRICICULE_ENDPOINT = ELEVE_ENDPOINT + "/filter/{matricule}";
    String FIND_ALL_ELEVE_ENDPOINT = ELEVE_ENDPOINT + "/all";
    String DELETE_ELEVE_ENDPOINT = ELEVE_ENDPOINT + "/delete/{idEleve}";


    String NIVEAU_ENDPOINT = APP_ROOT + "/niveaux";
    String CREATE_NIVEAU_ENDPOINT = NIVEAU_ENDPOINT + "/create";
    String FIND_NIVEAU_BY_ID_ENDPOINT = NIVEAU_ENDPOINT + "/{idNiveau}";
    String FIND_ALL_NIVEAUX_ENDPOINT = NIVEAU_ENDPOINT + "/all";
    String DELETE_NIVEAU_ENDPOINT = NIVEAU_ENDPOINT + "/delete/{idNiveau}";


    String INSCRIPTION_ENDPOINT = APP_ROOT + "/inscriptions";
    String CREATE_INSCRIPTION_ENDPOINT = INSCRIPTION_ENDPOINT + "/create";
    String FIND_INSCRIPTION_BY_ID_ENDPOINT = INSCRIPTION_ENDPOINT + "/{idInscription}";
    String FIND_ALL_INSCRIPTION_ENDPOINT = INSCRIPTION_ENDPOINT + "/all";
    String DELETE_INSCRIPTION_ENDPOINT = INSCRIPTION_ENDPOINT + "/delete/{idInscription}";

}
