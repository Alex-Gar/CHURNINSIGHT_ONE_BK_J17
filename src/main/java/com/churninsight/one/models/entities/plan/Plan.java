package com.churninsight.one.models.entities.plan;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "planes")
@EntityListeners(AuditingEntityListener.class)
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "servicio_telefono", nullable = false)
    private Boolean servicioTelefono;

    @Column(name = "servicio_internet", length = 20, nullable = false)
    private String servicioInternet;

    @Column(name = "seguridad_en_linea", nullable = false)
    private Boolean seguridadEnLinea;

    @Column(name = "respaldo_en_linea", nullable = false)
    private Boolean respaldoEnLinea;

    @Column(name = "proteccion_dispositivo", nullable = false)
    private Boolean proteccionDispositivo;

    @Column(name = "soporte_tecnico", nullable = false)
    private Boolean soporteTecnico;

    @Column(name = "lineas_multiples", nullable = false)
    private Boolean lineasMultiples;

    @Column(name = "streaming_tv", nullable = false)
    private Boolean streamingTv;

    @Column(name = "streaming_peliculas", nullable = false)
    private Boolean streamingPeliculas;

    @Column(name = "cargo_mensual", nullable = false)
    private BigDecimal cargoMensual;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getLineasMultiples() {
        return lineasMultiples;
    }

    public void setLineasMultiples(Boolean lineasMultiples) {
        this.lineasMultiples = lineasMultiples;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
