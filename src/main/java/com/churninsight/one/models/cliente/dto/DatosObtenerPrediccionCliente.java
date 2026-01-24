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
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record DatosObtenerPrediccionCliente(

        @JsonProperty("id_cliente")
        @Schema(example = "7592-VHVEG")
        String idCliente,

        @JsonProperty("genero")
        Genero genero,

        @JsonProperty("adulto_mayor")
        @JsonSerialize(using = BooleanTo01Serializer.class)
        @Schema(type = "integer", example = "1", description = "0 = No, 1 = Si")
        Boolean adultoMayor,

        @JsonProperty("tiene_pareja")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        @Schema(type = "string", example = "Yes")
        Boolean tienePareja,

        @JsonProperty("tiene_dependientes")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        @Schema(type = "string", example = "No")
        Boolean tieneDependientes,

        @JsonProperty("antiguedad_meses")
        @Schema(example = "12")
        Integer antiguedadMeses,

        @JsonProperty("servicio_telefono")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        @Schema(type = "string", example = "Yes")
        Boolean servicioTelefono,

        @JsonProperty("lineas_multiples")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        @Schema(type = "string", example = "No")
        Boolean lineasMultiples,

        @JsonProperty("servicio_internet")
        ServicioInternet servicioInternet,

        @JsonProperty("seguridad_en_linea")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        @Schema(type = "string", example = "No")
        Boolean seguridadEnLinea,

        @JsonProperty("respaldo_en_linea")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        @Schema(type = "string", example = "Yes")
        Boolean respaldoEnLinea,

        @JsonProperty("proteccion_dispositivo")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        @Schema(type = "string", example = "No")
        Boolean proteccionDispositivo,

        @JsonProperty("soporte_tecnico")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        @Schema(type = "string", example = "No")
        Boolean soporteTecnico,

        @JsonProperty("streaming_tv")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        @Schema(type = "string", example = "Yes")
        Boolean streamingTv,

        @JsonProperty("streaming_peliculas")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        @Schema(type = "string", example = "No")
        Boolean streamingPeliculas,

        @JsonProperty("tipo_contrato")
        TipoContrato tipoContrato,

        @JsonProperty("facturacion_electronica")
        @JsonSerialize(using = BooleanToYesNoSerializer.class)
        @Schema(type = "string", example = "Yes")
        Boolean facturacionElectronica,

        @JsonProperty("metodo_pago")
        MetodoPago metodoPago,

        @JsonProperty("cargo_mensual")
        @Schema(example = "70.35")
        BigDecimal cargoMensual,

        @JsonProperty("cargos_totales")
        @Schema(example = "844.20")
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