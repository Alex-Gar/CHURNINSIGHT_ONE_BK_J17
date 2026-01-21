package com.churninsight.one.models.entities.servicio;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ServicioDto(

        Long id,

        @NotBlank(message = "El id del usuario es obligatorio")
        @Size(max= 255, message= "El id del usuario no debe exceder 255 caracteres")
        String idUsuario,

        @NotNull(message = "El plan es obligatorio")
        Long idPlan,

        LocalDateTime ultimaFechaPago,

        @NotNull(message = "La facturación es obligatoria")
        Boolean facturacionElectronica,

        @NotNull(message = "El tipo de contrato es obligatorio")
        String tipoContrato,


        @NotNull(message = "La suscripción es obligatoria")
        Boolean subscripcionActiva,

        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt
) {
}
