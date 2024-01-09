package com.gestionEcole.gestionEcole.exception;

public enum ErrorCodes {

    ELEVE_NOT_FOUND(1000),
    ELEVE_NOT_VALID(1001),
    ELEVE_ALREADY_IN_USE(1002),

    NIVEAU_NOT_FOUND(2000),
    NIVEAU_NOT_VALID(2001),
    NIVEAU_ALREADY_IN_USE(2002),
    INSCRIPTION_NOT_FOUND(3000),
    INSCRIPTION_NOT_VALID(3001),
    INSCRIPTION_ALREADY_IN_USE(3002),

    ;

    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
