package com.churninsight.one.models.cliente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DatosDsPredict(

        @JsonProperty("abandono_cliente")
        Boolean churn,
        Double probabilidad

) {
}
