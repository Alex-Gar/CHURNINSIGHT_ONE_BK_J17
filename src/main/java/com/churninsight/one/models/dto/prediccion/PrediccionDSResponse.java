package com.churninsight.one.models.dto.prediccion;

public record PrediccionDSResponse(

        Boolean churn,

        String prevision,

        Double probabilidad
) { }
