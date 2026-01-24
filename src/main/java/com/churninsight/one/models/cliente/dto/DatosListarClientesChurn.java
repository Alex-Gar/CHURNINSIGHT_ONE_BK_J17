package com.churninsight.one.models.cliente.dto;

import com.churninsight.one.models.cliente.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record DatosListarClientesChurn(

        @Schema(description = "Identificador único del cliente", example = "7592-VHVEG")
        String idCliente,

        @Schema(description = "Meses de permanencia del cliente", example = "12")
        Integer antiguedadMeses,

        @Schema(description = "Costo mensual del servicio", example = "70.35")
        BigDecimal cargoMensual,

        @Schema(description = "Probabilidad de abandono calculada (0.0 a 1.0)", example = "0.15")
        Double probabilidad,

        @Schema(description = "Estado de fuga (true: se va, false: se queda)")
        Boolean churn

) {
    public DatosListarClientesChurn(Cliente cliente) {
        this(
                cliente.getIdCliente(),
                cliente.getAntiguedadMeses(),
                cliente.getCargoMensual(),
                cliente.getProbabilidad(),
                cliente.getChurn()
        );
    }
}