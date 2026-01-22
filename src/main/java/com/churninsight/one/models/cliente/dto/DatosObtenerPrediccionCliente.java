package com.churninsight.one.models.cliente.dto;

import com.churninsight.one.enums.Genero;
import com.churninsight.one.enums.MetodoPago;
import com.churninsight.one.enums.ServicioInternet;
import com.churninsight.one.enums.TipoContrato;
import com.churninsight.one.infra.jackson.BooleanTo01Serializer;
import com.churninsight.one.infra.jackson.BooleanToYesNoSerializer;
import com.churninsight.one.models.cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;

public record DatosObtenerPrediccionCliente(

        @JsonProperty("id_cliente")
        String idCliente,
        @JsonProperty("genero")
        Genero genero,
        @JsonProperty("adulto_mayor")
        @JsonSerialize(using = BooleanTo01Serializer.class)
        Boolean adultoMayor,
        @JsonProperty("tiene_pareja")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        Boolean tienePareja,
        @JsonProperty("tiene_dependientes")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        Boolean tieneDependientes,
        @JsonProperty("antiguedad_meses")
        Integer antiguedadMeses,
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        @JsonProperty("servicio_telefono")
        Boolean servicioTelefono,
        @JsonProperty("lineas_multiples")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        Boolean lineasMultiples,
        @JsonProperty("servicio_internet")
        ServicioInternet servicioInternet,
        @JsonProperty("seguridad_en_linea")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        Boolean seguridadEnLinea,
        @JsonProperty("respaldo_en_linea")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        Boolean respaldoEnLinea,
        @JsonProperty("proteccion_dispositivo")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        Boolean proteccionDispositivo,
        @JsonProperty("soporte_tecnico")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        Boolean soporteTecnico,
        @JsonProperty("streaming_tv")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        Boolean streamingTv,
        @JsonProperty("streaming_peliculas")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        Boolean streamingPeliculas,
        @JsonProperty("tipo_contrato")
        TipoContrato tipoContrato,
        @JsonProperty("facturacion_electronica")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        Boolean facturacionElectronica,
        @JsonProperty("metodo_pago")
        MetodoPago metodoPago,
        @JsonProperty("cargo_mensual")
        BigDecimal cargoMensual,
        @JsonProperty("cargos_totales")
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
