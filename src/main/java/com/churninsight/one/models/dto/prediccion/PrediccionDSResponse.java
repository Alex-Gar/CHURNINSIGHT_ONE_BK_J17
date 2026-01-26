package com.churninsight.one.models.dto.prediccion;

import com.fasterxml.jackson.annotation.JsonAlias;

public record PrediccionDSResponse(
        String id_cliente,
        Boolean churn,
        String prevision,
        @JsonAlias("probability") Double probabilidad) {
}
