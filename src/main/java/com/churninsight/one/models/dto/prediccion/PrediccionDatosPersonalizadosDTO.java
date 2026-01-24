package com.churninsight.one.models.dto.prediccion;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO para recibir datos personalizados para predicción de churn.
 * Permite que el frontend envíe datos modificados directamente sin depender
 * de los datos almacenados en la vista SQL.
 */
public class PrediccionDatosPersonalizadosDTO {

    @JsonProperty("id_cliente")
    private String idCliente;

    @JsonProperty("genero")
    private String genero;

    @JsonProperty("adulto_mayor")
    private Integer adultoMayor;

    @JsonProperty("tiene_pareja")
    private String tienePareja;

    @JsonProperty("tiene_dependientes")
    private String tieneDependientes;

    @JsonProperty("antiguedad_meses")
    private Integer antiguedadMeses;

    @JsonProperty("servicio_telefono")
    private String servicioTelefono;

    @JsonProperty("lineas_multiples")
    private String lineasMultiples;

    @JsonProperty("servicio_internet")
    private String servicioInternet;

    @JsonProperty("seguridad_en_linea")
    private String seguridadEnLinea;

    @JsonProperty("respaldo_en_linea")
    private String respaldoEnLinea;

    @JsonProperty("proteccion_dispositivo")
    private String proteccionDispositivo;

    @JsonProperty("soporte_tecnico")
    private String soporteTecnico;

    @JsonProperty("streaming_tv")
    private String streamingTv;

    @JsonProperty("streaming_peliculas")
    private String streamingPeliculas;

    @JsonProperty("tipo_contrato")
    private String tipoContrato;

    @JsonProperty("facturacion_electronica")
    private String facturacionElectronica;

    @JsonProperty("metodo_pago")
    private String metodoPago;

    @JsonProperty("cargo_mensual")
    private BigDecimal cargoMensual;

    @JsonProperty("cargos_totales")
    private BigDecimal cargosTotales;

    // Constructor vacío para Jackson
    public PrediccionDatosPersonalizadosDTO() {}

    // Constructor completo para testing
    public PrediccionDatosPersonalizadosDTO(
        String idCliente, String genero, Integer adultoMayor, String tienePareja,
        String tieneDependientes, Integer antiguedadMeses, String servicioTelefono,
        String lineasMultiples, String servicioInternet, String seguridadEnLinea,
        String respaldoEnLinea, String proteccionDispositivo, String soporteTecnico,
        String streamingTv, String streamingPeliculas, String tipoContrato,
        String facturacionElectronica, String metodoPago, BigDecimal cargoMensual,
        BigDecimal cargosTotales
    ) {
        this.idCliente = idCliente;
        this.genero = genero;
        this.adultoMayor = adultoMayor;
        this.tienePareja = tienePareja;
        this.tieneDependientes = tieneDependientes;
        this.antiguedadMeses = antiguedadMeses;
        this.servicioTelefono = servicioTelefono;
        this.lineasMultiples = lineasMultiples;
        this.servicioInternet = servicioInternet;
        this.seguridadEnLinea = seguridadEnLinea;
        this.respaldoEnLinea = respaldoEnLinea;
        this.proteccionDispositivo = proteccionDispositivo;
        this.soporteTecnico = soporteTecnico;
        this.streamingTv = streamingTv;
        this.streamingPeliculas = streamingPeliculas;
        this.tipoContrato = tipoContrato;
        this.facturacionElectronica = facturacionElectronica;
        this.metodoPago = metodoPago;
        this.cargoMensual = cargoMensual;
        this.cargosTotales = cargosTotales;
    }

    // Getters y Setters
    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getAdultoMayor() {
        return adultoMayor;
    }

    public void setAdultoMayor(Integer adultoMayor) {
        this.adultoMayor = adultoMayor;
    }

    public String getTienePareja() {
        return tienePareja;
    }

    public void setTienePareja(String tienePareja) {
        this.tienePareja = tienePareja;
    }

    public String getTieneDependientes() {
        return tieneDependientes;
    }

    public void setTieneDependientes(String tieneDependientes) {
        this.tieneDependientes = tieneDependientes;
    }

    public Integer getAntiguedadMeses() {
        return antiguedadMeses;
    }

    public void setAntiguedadMeses(Integer antiguedadMeses) {
        this.antiguedadMeses = antiguedadMeses;
    }

    public String getServicioTelefono() {
        return servicioTelefono;
    }

    public void setServicioTelefono(String servicioTelefono) {
        this.servicioTelefono = servicioTelefono;
    }

    public String getLineasMultiples() {
        return lineasMultiples;
    }

    public void setLineasMultiples(String lineasMultiples) {
        this.lineasMultiples = lineasMultiples;
    }

    public String getServicioInternet() {
        return servicioInternet;
    }

    public void setServicioInternet(String servicioInternet) {
        this.servicioInternet = servicioInternet;
    }

    public String getSeguridadEnLinea() {
        return seguridadEnLinea;
    }

    public void setSeguridadEnLinea(String seguridadEnLinea) {
        this.seguridadEnLinea = seguridadEnLinea;
    }

    public String getRespaldoEnLinea() {
        return respaldoEnLinea;
    }

    public void setRespaldoEnLinea(String respaldoEnLinea) {
        this.respaldoEnLinea = respaldoEnLinea;
    }

    public String getProteccionDispositivo() {
        return proteccionDispositivo;
    }

    public void setProteccionDispositivo(String proteccionDispositivo) {
        this.proteccionDispositivo = proteccionDispositivo;
    }

    public String getSoporteTecnico() {
        return soporteTecnico;
    }

    public void setSoporteTecnico(String soporteTecnico) {
        this.soporteTecnico = soporteTecnico;
    }

    public String getStreamingTv() {
        return streamingTv;
    }

    public void setStreamingTv(String streamingTv) {
        this.streamingTv = streamingTv;
    }

    public String getStreamingPeliculas() {
        return streamingPeliculas;
    }

    public void setStreamingPeliculas(String streamingPeliculas) {
        this.streamingPeliculas = streamingPeliculas;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getFacturacionElectronica() {
        return facturacionElectronica;
    }

    public void setFacturacionElectronica(String facturacionElectronica) {
        this.facturacionElectronica = facturacionElectronica;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public BigDecimal getCargoMensual() {
        return cargoMensual;
    }

    public void setCargoMensual(BigDecimal cargoMensual) {
        this.cargoMensual = cargoMensual;
    }

    public BigDecimal getCargosTotales() {
        return cargosTotales;
    }

    public void setCargosTotales(BigDecimal cargosTotales) {
        this.cargosTotales = cargosTotales;
    }

    @Override
    public String toString() {
        return "PrediccionDatosPersonalizadosDTO{" +
                "idCliente='" + idCliente + '\'' +
                ", genero='" + genero + '\'' +
                ", adultoMayor=" + adultoMayor +
                ", tienePareja='" + tienePareja + '\'' +
                ", tieneDependientes='" + tieneDependientes + '\'' +
                ", antiguedadMeses=" + antiguedadMeses +
                ", servicioTelefono='" + servicioTelefono + '\'' +
                ", lineasMultiples='" + lineasMultiples + '\'' +
                ", servicioInternet='" + servicioInternet + '\'' +
                ", seguridadEnLinea='" + seguridadEnLinea + '\'' +
                ", respaldoEnLinea='" + respaldoEnLinea + '\'' +
                ", proteccionDispositivo='" + proteccionDispositivo + '\'' +
                ", soporteTecnico='" + soporteTecnico + '\'' +
                ", streamingTv='" + streamingTv + '\'' +
                ", streamingPeliculas='" + streamingPeliculas + '\'' +
                ", tipoContrato='" + tipoContrato + '\'' +
                ", facturacionElectronica='" + facturacionElectronica + '\'' +
                ", metodoPago='" + metodoPago + '\'' +
                ", cargoMensual=" + cargoMensual +
                ", cargosTotales=" + cargosTotales +
                '}';
    }
}