package com.churninsight.one.models.entities.plan;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PlanDto(
        Long id,
        @NotNull(message = "El servicio de teléfono es obligatorio") Boolean servicioTelefono,
        @NotNull(message = "El servicio de internet es obligatorio") @Size(max = 20) String servicioInternet,
        @NotNull(message = "La seguridad en línea es obligatoria") Boolean seguridadEnLinea,
        @NotNull(message = "El respaldo en línea es obligatorio") Boolean respaldoEnLinea,
        @NotNull(message = "La protección de dispositivo es obligatoria") Boolean proteccionDispositivo,
        @NotNull(message = "El soporte técnico es obligatorio") Boolean soporteTecnico,
        @NotNull(message = "Las líneas múltiples son obligatorias") Boolean lineasMultiples,
        @NotNull(message = "Streaming TV es obligatorio") Boolean streamingTv,
        @NotNull(message = "Streaming películas es obligatorio") Boolean streamingPeliculas,
        @NotNull(message = "El cargo mensual es obligatorio") BigDecimal cargoMensual,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt) {
}
