package com.churninsight.one.models.entities.vistas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vw_servicios_usuarios")
@Immutable
public class ServicioUsuarioView {

    @Id
    @Column(name = "id_cliente")
    private String idCliente;

    private String genero;

    private Integer adulto;

    @Column(name = "tiene_pareja")
    private Boolean tienePareja;

    @Column(name = "tiene_dependientes")
    private Boolean tieneDependientes;

    @Column(name = "antiguedad_meses")
    private BigDecimal antiguedadmeses;

    @Column(name = "id_servicio")
    private Long idServicio;

    @Column(name = "tipo_contrato")
    private String tipoContrato;

    @Column(name = "facturacion_electronica")
    private Boolean facturacionElectronica;

    @Column(name = "subscripcion_activa")
    private Boolean subscripcionActiva;

    @Column(name = "ultima_fecha_pago")
    private LocalDateTime ultimaFechaPago;

    @Column(name = "id_plan")
    private Long idPlan;

    @Column(name = "servicio_telefono")
    private Boolean servicioTelefono;

    @Column(name = "servicio_internet")
    private String servicioInternet;

    @Column(name = "seguridad_en_linea")
    private Boolean seguridadEnLinea;

    @Column(name = "respaldo_en_linea")
    private Boolean respaldoEnLinea;

    @Column(name = "proteccion_dispositivo")
    private Boolean proteccionDispositivo;

    @Column(name = "soporte_tecnico")
    private Boolean soporteTecnico;

    @Column(name = "streaming_tv")
    private Boolean streamingTv;

    @Column(name = "streaming_peliculas")
    private Boolean streamingPeliculas;

    @Column(name = "cargo_mensual")
    private BigDecimal cargoMensual;

    @Column(name = "cargos_totales")
    private BigDecimal cargosTotales;

    @Column(name = "fecha_alta_cliente")
    private LocalDateTime fechaAltaCliente;

    @Column(name = "fecha_alta_servicio")
    private LocalDateTime fechaAltaServicio;

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

    public Integer getAdulto() {
        return adulto;
    }

    public void setAdulto(Integer adulto) {
        this.adulto = adulto;
    }

    public Boolean getTienePareja() {
        return tienePareja;
    }

    public void setTienePareja(Boolean tienePareja) {
        this.tienePareja = tienePareja;
    }

    public Boolean getTieneDependientes() {
        return tieneDependientes;
    }

    public void setTieneDependientes(Boolean tieneDependientes) {
        this.tieneDependientes = tieneDependientes;
    }

    public BigDecimal getAntiguedadmeses() {
        return antiguedadmeses;
    }

    public void setAntiguedadmeses(BigDecimal antiguedadmeses) {
        this.antiguedadmeses = antiguedadmeses;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Boolean getFacturacionElectronica() {
        return facturacionElectronica;
    }

    public void setFacturacionElectronica(Boolean facturacionElectronica) {
        this.facturacionElectronica = facturacionElectronica;
    }

    public Boolean getSubscripcionActiva() {
        return subscripcionActiva;
    }

    public void setSubscripcionActiva(Boolean subscripcionActiva) {
        this.subscripcionActiva = subscripcionActiva;
    }

    public LocalDateTime getUltimaFechaPago() {
        return ultimaFechaPago;
    }

    public void setUltimaFechaPago(LocalDateTime ultimaFechaPago) {
        this.ultimaFechaPago = ultimaFechaPago;
    }

    public Long getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Long idPlan) {
        this.idPlan = idPlan;
    }

    public Boolean getServicioTelefono() {
        return servicioTelefono;
    }

    public void setServicioTelefono(Boolean servicioTelefono) {
        this.servicioTelefono = servicioTelefono;
    }

    public String getServicioInternet() {
        return servicioInternet;
    }

    public void setServicioInternet(String servicioInternet) {
        this.servicioInternet = servicioInternet;
    }

    public Boolean getSeguridadEnLinea() {
        return seguridadEnLinea;
    }

    public void setSeguridadEnLinea(Boolean seguridadEnLinea) {
        this.seguridadEnLinea = seguridadEnLinea;
    }

    public Boolean getRespaldoEnLinea() {
        return respaldoEnLinea;
    }

    public void setRespaldoEnLinea(Boolean respaldoEnLinea) {
        this.respaldoEnLinea = respaldoEnLinea;
    }

    public Boolean getProteccionDispositivo() {
        return proteccionDispositivo;
    }

    public void setProteccionDispositivo(Boolean proteccionDispositivo) {
        this.proteccionDispositivo = proteccionDispositivo;
    }

    public Boolean getSoporteTecnico() {
        return soporteTecnico;
    }

    public void setSoporteTecnico(Boolean soporteTecnico) {
        this.soporteTecnico = soporteTecnico;
    }

    public Boolean getStreamingTv() {
        return streamingTv;
    }

    public void setStreamingTv(Boolean streamingTv) {
        this.streamingTv = streamingTv;
    }

    public Boolean getStreamingPeliculas() {
        return streamingPeliculas;
    }

    public void setStreamingPeliculas(Boolean streamingPeliculas) {
        this.streamingPeliculas = streamingPeliculas;
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

    public LocalDateTime getFechaAltaCliente() {
        return fechaAltaCliente;
    }

    public void setFechaAltaCliente(LocalDateTime fechaAltaCliente) {
        this.fechaAltaCliente = fechaAltaCliente;
    }

    public LocalDateTime getFechaAltaServicio() {
        return fechaAltaServicio;
    }

    public void setFechaAltaServicio(LocalDateTime fechaAltaServicio) {
        this.fechaAltaServicio = fechaAltaServicio;
    }

}
