package com.churninsight.one.models.cliente.dto;

import com.churninsight.one.enums.Genero;
import com.churninsight.one.enums.MetodoPago;
import com.churninsight.one.enums.ServicioInternet;
import com.churninsight.one.enums.TipoContrato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DatosConsultaChurnCliente(

        @NotBlank(message = "idCliente es obligatorio")
        String idCliente,

        @NotNull(message = "genero es obligatorio")
        Genero genero,

        @NotNull(message = "adultoMayor es obligatorio")
        Boolean adultoMayor,

        @NotNull(message = "tienePareja es obligatorio")
        Boolean tienePareja,

        @NotNull(message = "tieneDependientes es obligatorio")
        Boolean tieneDependientes,

        @NotNull(message = "antiguedadMeses es obligatorio")
        Integer antiguedadMeses,

        @NotNull(message = "servicioTelefono es obligatorio")
        Boolean servicioTelefono,

        @NotNull(message = "lineasMultiples es obligatorio")
        Boolean lineasMultiples,

        @NotNull(message = "servicioInternet es obligatorio")
        ServicioInternet servicioInternet,

        @NotNull(message = "seguridadEnLinea es obligatorio")
        Boolean seguridadEnLinea,

        @NotNull(message = "respaldoEnLinea es obligatorio")
        Boolean respaldoEnLinea,

        @NotNull(message = "proteccionDispositivo es obligatorio")
        Boolean proteccionDispositivo,

        @NotNull(message = "soporteTecnico es obligatorio")
        Boolean soporteTecnico,

        @NotNull(message = "streamingTv es obligatorio")
        Boolean streamingTv,

        @NotNull(message = "streamingPeliculas es obligatorio")
        Boolean streamingPeliculas,

        @NotNull(message = "tipoContrato es obligatorio")
        TipoContrato tipoContrato,

        @NotNull(message = "facturacionElectronica es obligatorio")
        Boolean facturacionElectronica,

        @NotNull(message = "metodoPago es obligatorio")
        MetodoPago metodoPago,

        @NotNull(message = "cargoMensual es obligatorio")
        BigDecimal cargoMensual,

        @NotNull(message = "cargosTotales es obligatorio")
        BigDecimal cargosTotales

) {

}
