package com.churninsight.one.models.cliente.dto;

import com.churninsight.one.models.cliente.Cliente;

import java.math.BigDecimal;

public record DatosDetalleChurnCliente(

        String idCliente,
        Integer antiguedadMeses,
        String tipoContrato,
        BigDecimal cargoMensual,
        Double probabilidadChurn,
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
