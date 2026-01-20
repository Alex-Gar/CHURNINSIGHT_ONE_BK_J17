package com.churninsight.one.models.entities.vistas;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record outputModelViewDto(

        @JsonProperty("id_cliente") String idCliente,

        @JsonProperty("genero") String genero,

        @JsonProperty("adulto_mayor") Integer adultoMayor,

        @JsonProperty("tiene_pareja") String tienePareja,

        @JsonProperty("tiene_dependientes") String tieneDependientes,

        @JsonProperty("antiguedad_meses") Integer antiguedadMeses,

        @JsonProperty("servicio_telefono") String servicioTelefono,

        @JsonProperty("lineas_multiples") String lineasMultiples,

        @JsonProperty("servicio_internet") String servicioInternet,

        @JsonProperty("seguridad_en_linea") String seguridadEnLinea,

        @JsonProperty("respaldo_en_linea") String respaldoEnLinea,

        @JsonProperty("proteccion_dispositivo") String proteccionDispositivo,

        @JsonProperty("soporte_tecnico") String soporteTecnico,

        @JsonProperty("streaming_tv") String streamingTv,

        @JsonProperty("streaming_peliculas") String streamingPeliculas,

        @JsonProperty("tipo_contrato") String tipoContrato,

        @JsonProperty("facturacion_electronica") String facturacionElectronica,

        @JsonProperty("metodo_pago") String metodoPago,

        @JsonProperty("cargo_mensual") BigDecimal cargoMensual,

        @JsonProperty("cargos_totales") BigDecimal cargosTotales

) {
}
