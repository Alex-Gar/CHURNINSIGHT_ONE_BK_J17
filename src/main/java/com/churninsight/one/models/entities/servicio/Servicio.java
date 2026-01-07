package com.churninsight.one.models.entities.servicio;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name= "servicios")
@EntityListeners(AuditingEntityListener.class)
public class Servicio {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name= "id_plan")
    private Integer idPlan;

    @Column(name= "ultima_fecha_pago")
    private LocalDate ultimaFechaPago;

    @Column(name = "facturacion_electronica")
    private Boolean facturacion;

    @Column(name = "tipo_contrato")
    private long tipoContrato;

    @Column(name= "id_usuario")
    private String idUsuario;

    @Column(name= "subscripcion_activa")
    private Boolean subscription;

    @CreatedDate
    @Column(name= "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name= "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public LocalDate getUltimaFechaPago() {
        return ultimaFechaPago;
    }

    public void setUltimaFechaPago(LocalDate ultimaFechaPago) {
        this.ultimaFechaPago = ultimaFechaPago;
    }

    public Boolean getFacturacion() {
        return facturacion;
    }

    public void setFacturacion(Boolean facturacion) {
        this.facturacion = facturacion;
    }

    public long getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(long tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Boolean getSubscription() {
        return subscription;
    }

    public void setSubscription(Boolean subscription) {
        this.subscription = subscription;
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
