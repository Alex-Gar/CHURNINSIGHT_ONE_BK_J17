package com.churninsight.one.models.cliente.dto;

import com.churninsight.one.enums.Genero;
import com.churninsight.one.enums.MetodoPago;
import com.churninsight.one.enums.ServicioInternet;
import com.churninsight.one.enums.TipoContrato;
import com.churninsight.one.models.cliente.Cliente;

import java.math.BigDecimal;

public record DatosObtenerPrediccionCliente(

        String idCliente,
        Genero genero,
        Boolean adultoMayor,
        Boolean tienePareja,
        Boolean tieneDependientes,
        Integer antiguedadMeses,
        Boolean servicioTelefono,
        Boolean lineasMultiples,
        ServicioInternet servicioInternet,
        Boolean seguridadEnLinea,
        Boolean respaldoEnLinea,
        Boolean proteccionDispositivo,
        Boolean soporteTecnico,
        Boolean streamingTv,
        Boolean streamingPeliculas,
        TipoContrato tipoContrato,
        Boolean facturacionElectronica,
        MetodoPago metodoPago,
        BigDecimal cargoMensual,
        BigDecimal cargosTotales

) {

    public DatosObtenerPrediccionCliente(Cliente cliente) {
        this(
                cliente.getIdCliente(),
                cliente.getGenero(),
                cliente.getAdultoMayor(),
                cliente.getTienePareja(),
                cliente.getTieneDependientes(),
                cliente.getAntiguedadMeses(),
                cliente.getServicioTelefono(),
                cliente.getLineasMultiples(),
                cliente.getServicioInternet(),
                cliente.getSeguridadEnLinea(),
                cliente.getRespaldoEnLinea(),
                cliente.getProteccionDispositivo(),
                cliente.getSoporteTecnico(),
                cliente.getStreamingTv(),
                cliente.getStreamingPeliculas(),
                cliente.getTipoContrato(),
                cliente.getFacturacionElectronica(),
                cliente.getMetodoPago(),
                cliente.getCargoMensual(),
                cliente.getCargosTotales()
        );
    }
}
