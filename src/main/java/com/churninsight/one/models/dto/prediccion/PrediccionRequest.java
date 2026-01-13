package com.churninsight.one.models.dto.prediccion;

public record PrediccionRequest(
        String idUsuario,
        Integer edad,
        Integer ternure,
        Double usoMensual,
        String plan
) {
}
