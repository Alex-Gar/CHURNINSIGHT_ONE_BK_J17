package com.churninsight.one.models.dto.prediccion;

import java.time.LocalDateTime;

public record PrediccionResponse(
        Long id,
        String idUsuario,
        Boolean churn,
        String prevision,
        Double probabilidad,
        LocalDateTime createdAt

) {
}
