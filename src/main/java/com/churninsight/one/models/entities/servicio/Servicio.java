package com.churninsight.one.models.entities.servicio;

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
@Table(name = "servicios")
@EntityListeners(AuditingEntityListener.class)
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario")
    private String idUsuario;

    @Column(name = "id_plan")
    private Long idPlan;

    @Column(name = "ultima_fecha_pago")
    private LocalDateTime ultimaFechaPago;

    @Column(name = "facturacion_electronica")
    private Boolean facturacionElectronica;

    @Column(name = "tipo_contrato")
    private String tipoContrato;

    @Column(name = "subscripcion_activa")
    private Boolean subscripcionActiva;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Long idPlan) {
        this.idPlan = idPlan;
    }

    public LocalDateTime getUltimaFechaPago() {
        return ultimaFechaPago;
    }

    public void setUltimaFechaPago(LocalDateTime ultimaFechaPago) {
        this.ultimaFechaPago = ultimaFechaPago;
    }

    public Boolean getFacturacionElectronica() {
        return facturacionElectronica;
    }

    public void setFacturacionElectronica(Boolean facturacionElectronica) {
        this.facturacionElectronica = facturacionElectronica;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Boolean getSubscripcionActiva() {
        return subscripcionActiva;
    }

    public void setSubscripcionActiva(Boolean subscripcionActiva) {
        this.subscripcionActiva = subscripcionActiva;
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