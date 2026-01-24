package com.churninsight.one.models.cliente.dto;

import com.churninsight.one.enums.Genero;
import com.churninsight.one.enums.MetodoPago;
import com.churninsight.one.enums.ServicioInternet;
import com.churninsight.one.enums.TipoContrato;
import com.churninsight.one.infra.jackson.YesNoToBooleanDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record DatosActualizarCliente(

        Genero genero,

        @JsonProperty("adulto_mayor")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @Schema(type = "string", allowableValues = {"Yes", "No"}, example = "No")
        Boolean adultoMayor,

        @JsonProperty("tiene_pareja")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @Schema(type = "string", allowableValues = {"Yes", "No"}, example = "Yes")
        Boolean tienePareja,

        @JsonProperty("tiene_dependientes")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @Schema(type = "string", allowableValues = {"Yes", "No"}, example = "No")
        Boolean tieneDependientes,

        @JsonProperty("antiguedad_meses")
        @Schema(example = "24")
        Integer antiguedadMeses,

        @JsonProperty("servicio_telefono")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @Schema(type = "string", allowableValues = {"Yes", "No"}, example = "Yes")
        Boolean servicioTelefono,

        @JsonProperty("lineas_multiples")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @Schema(type = "string", allowableValues = {"Yes", "No", "No phone service"}, example = "No")
        Boolean lineasMultiples,

        @JsonProperty("servicio_internet")
        ServicioInternet servicioInternet,

        @JsonProperty("seguridad_en_linea")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @Schema(type = "string", allowableValues = {"Yes", "No"}, example = "No")
        Boolean seguridadEnLinea,

        @JsonProperty("respaldo_en_linea")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @Schema(type = "string", allowableValues = {"Yes", "No"}, example = "Yes")
        Boolean respaldoEnLinea,

        @JsonProperty("proteccion_dispositivo")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @Schema(type = "string", allowableValues = {"Yes", "No"}, example = "No")
        Boolean proteccionDispositivo,

        @JsonProperty("soporte_tecnico")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @Schema(type = "string", allowableValues = {"Yes", "No"}, example = "No")
        Boolean soporteTecnico,

        @JsonProperty("streaming_tv")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @Schema(type = "string", allowableValues = {"Yes", "No"}, example = "Yes")
        Boolean streamingTv,

        @JsonProperty("streaming_peliculas")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @Schema(type = "string", allowableValues = {"Yes", "No"}, example = "No")
        Boolean streamingPeliculas,

        @JsonProperty("tipo_contrato")
        TipoContrato tipoContrato,

        @JsonProperty("facturacion_electronica")
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        @Schema(type = "string", allowableValues = {"Yes", "No"}, example = "Yes")
        Boolean facturacionElectronica,

        @JsonProperty("metodo_pago")
        MetodoPago metodoPago,

        @JsonProperty("cargo_mensual")
        @Schema(example = "75.50")
        BigDecimal cargoMensual,

        @JsonProperty("cargos_totales")
        @Schema(example = "1812.00")
        BigDecimal cargosTotales
) {
}