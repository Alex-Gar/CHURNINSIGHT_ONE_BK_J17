package com.churninsight.one.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ServicioInternet {

    FIBER_OPTIC,
    DSL,
    NONE;

    @JsonCreator
    public static final ServicioInternet from(String data) {
        if (data == null || data.isBlank()) {
            return null;
        }
        try {
            return ServicioInternet.valueOf(data.trim().toUpperCase().replace(" ", "_").replace("-", "_"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El servicioInternet " + data + "no es valido");
        }

    }

}
