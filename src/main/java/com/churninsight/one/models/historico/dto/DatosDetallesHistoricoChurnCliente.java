package com.churninsight.one.models.historico.dto;

import com.churninsight.one.models.historico.Historico;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record DatosDetallesHistoricoChurnCliente(

        @Schema(description = "Fecha y hora en que se realizó la predicción", example = "2025-09-28T14:30:00")
        LocalDateTime fechaPrediccion,

        @Schema(description = "Probabilidad de fuga calculada por la IA (0.0 a 1.0)", example = "0.15")
        Double probabilidad,

        @Schema(description = "Resultado final de la predicción (true si hay fuga, false si se queda)")
        Boolean churn

) {

    public DatosDetallesHistoricoChurnCliente(Historico historico) {
        this(
                historico.getFechaPrediccion(),
                historico.getProbabilidad(),
                historico.getChurn()
        );
    }
}