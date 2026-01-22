package com.churninsight.one.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Genero {
    MALE,
    FEMALE,
    OTHER;

    @JsonCreator
    public static Genero from(String data) {
        if (data == null || data.isBlank()) {
            return null;
        }
        try {
            return Genero.valueOf(data.trim().toUpperCase().replace(" ", "_").replace("-", "_"));
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("El Genero" + data + "No es Valido!");
        }
    }
}