package com.churninsight.one.models.cliente.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record DatosDsPredict(

        @Schema(description = "Resultado booleano de la predicción de fuga", example = "false")
        Boolean churn,

        @Schema(description = "Texto descriptivo de la previsión", example = "No Churn")
        String prevision,

        @Schema(description = "Valor numérico de la probabilidad (0.0 a 1.0)", example = "0.1542")
        Double probabilidad

) {
}