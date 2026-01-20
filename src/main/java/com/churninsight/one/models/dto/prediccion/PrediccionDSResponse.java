package com.churninsight.one.models.dto.prediccion;

public record PrediccionDSResponse(
                String id_cliente,
                Boolean churn,
                String prevision,
                Double probabilidad
) {
}
