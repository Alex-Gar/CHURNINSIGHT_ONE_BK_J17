package com.churninsight.one.models.entities.historialPredicciones;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record HistorialPrediccionDto(
        Long id,
        @NotNull(message = "El churn es obligatoria") Boolean churn,
        @NotNull(message = "La provision es obligatoria") String prevision,
        @NotNull(message = "La probabilidad es obligatoria") Double probabilidad,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt

) {

}
