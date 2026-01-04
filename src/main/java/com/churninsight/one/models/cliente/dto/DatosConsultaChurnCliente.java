package com.churninsight.one.models.cliente.dto;

import com.churninsight.one.enums.Genero;
import com.churninsight.one.enums.MetodoPago;
import com.churninsight.one.enums.ServicioInternet;
import com.churninsight.one.enums.TipoContrato;
import com.churninsight.one.infra.jackson.YesNoToBooleanDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DatosConsultaChurnCliente(

        @JsonProperty("id_cliente")
        @NotBlank(message = "idCliente es obligatorio")
        String idCliente,


        @NotNull(message = "genero es obligatorio")
        Genero genero,

        @JsonProperty("adulto_mayor")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @NotNull(message = "adultoMayor es obligatorio")
        Boolean adultoMayor,

        @JsonProperty("tiene_pareja")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @NotNull(message = "tienePareja es obligatorio")
        Boolean tienePareja,

        @JsonProperty("tiene_dependientes")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @NotNull(message = "tieneDependientes es obligatorio")
        Boolean tieneDependientes,

        @JsonProperty("antiguedad_meses")
        @NotNull(message = "antiguedadMeses es obligatorio")
        Integer antiguedadMeses,

        @JsonProperty("servicio_telefono")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @NotNull(message = "servicioTelefono es obligatorio")
        Boolean servicioTelefono,

        @JsonProperty("lineas_multiples")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @NotNull(message = "lineasMultiples es obligatorio")
        Boolean lineasMultiples,

        @JsonProperty("servicio_internet")
        @NotNull(message = "servicioInternet es obligatorio")
        ServicioInternet servicioInternet,

        @JsonProperty("seguridad_en_linea")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @NotNull(message = "seguridadEnLinea es obligatorio")
        Boolean seguridadEnLinea,

        @JsonProperty("respaldo_en_linea")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @NotNull(message = "respaldoEnLinea es obligatorio")
        Boolean respaldoEnLinea,

        @JsonProperty("proteccion_dispositivo")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @NotNull(message = "proteccionDispositivo es obligatorio")
        Boolean proteccionDispositivo,

        @JsonProperty("soporte_tecnico")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @NotNull(message = "soporteTecnico es obligatorio")
        Boolean soporteTecnico,

        @JsonProperty("streaming_tv")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @NotNull(message = "streamingTv es obligatorio")
        Boolean streamingTv,

        @JsonProperty("streaming_peliculas")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @NotNull(message = "streamingPeliculas es obligatorio")
        Boolean streamingPeliculas,

        @JsonProperty("tipo_contrato")
        @NotNull(message = "tipoContrato es obligatorio")
        TipoContrato tipoContrato,

        @JsonProperty("facturacion_electronica")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @NotNull(message = "facturacionElectronica es obligatorio")
        Boolean facturacionElectronica,

        @JsonProperty("metodo_pago")
        @NotNull(message = "metodoPago es obligatorio")
        MetodoPago metodoPago,

        @JsonProperty("cargo_mensual")
        @NotNull(message = "cargoMensual es obligatorio")
        BigDecimal cargoMensual,

        @JsonProperty("cargos_totales")
        @NotNull(message = "cargosTotales es obligatorio")
        BigDecimal cargosTotales

) {

}
