package com.churninsight.one.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Genero {
    MALE,
    FEMALE,
    OTHER;

    @JsonCreator
    public static Genero from(String data){
        if (data == null || data.isBlank()){
            return null;
        }
        return Genero.valueOf(data.trim().toUpperCase());
    }
}
