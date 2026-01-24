package com.churninsight.one.models.cliente.dto;

import com.churninsight.one.models.cliente.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record DatosDetalleChurnCliente(

        @Schema(description = "Identificador del cliente procesado", example = "7592-VHVEG")
        String idCliente,

        @Schema(description = "Meses que el cliente ha estado en la compañía", example = "12")
        Integer antiguedadMeses,

        @Schema(description = "Tipo de contrato actual", example = "MONTH_TO_MONTH")
        String tipoContrato,

        @Schema(description = "Monto del cargo mensual", example = "70.35")
        BigDecimal cargoMensual,

        @Schema(description = "Nivel de riesgo de fuga calculado por el modelo (0.0 a 1.0)", example = "0.85")
        Double probabilidad,

        @Schema(description = "Indica si el sistema predice que el cliente se fugará")
        Boolean churn

) {
    public DatosDetalleChurnCliente(Cliente cliente) {
        this(
                cliente.getIdCliente(),
                cliente.getAntiguedadMeses(),
                cliente.getTipoContrato().name(),
                cliente.getCargoMensual(),
                cliente.getProbabilidad(),
                cliente.getChurn()
        );
    }
}