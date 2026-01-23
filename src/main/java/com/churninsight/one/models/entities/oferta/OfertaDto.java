package com.churninsight.one.models.entities.oferta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OfertaDto(
        Long id,
        @NotBlank(message = "El nombre de la oferta es obligatorio") @Size(max = 100) String nombre,
        String descripcion,
        BigDecimal descuentoPorcentaje,
        BigDecimal descuentoMonto,
        Integer duracionMeses,
        Boolean aplicaChurn,
        Boolean activa,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt) {
}
