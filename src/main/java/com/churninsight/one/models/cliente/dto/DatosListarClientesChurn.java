package com.churninsight.one.models.cliente.dto;

import com.churninsight.one.models.cliente.Cliente;

import java.math.BigDecimal;

public record DatosListarClientesChurn(

        String idCliente,
        Integer antiguedadMeses,
        BigDecimal cargoMensual,
        Double probabilidad,
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
