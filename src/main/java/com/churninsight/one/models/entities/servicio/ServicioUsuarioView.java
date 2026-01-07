package com.churninsight.one.models.entities.servicio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name= "vw_servicios_usuarios")
@Immutable
public class ServicioUsuarioView {

    @Id
    @Column(name= "id_servicio")
    private Long idServicio;

    @Column(name = "id_cliente")
    private String idCliente;

    private String genero;
    private Integer adulto;

    @Column(name= "tiene_pareja")
    private Boolean tienePareja;

    @Column(name = "tiene_dependientes")
    private Boolean tieneDependientes;

    @Column(name= "antiguedad_meses")
    private Integer antiguedadmeses;

    @Column(name= "tipo_contrato")
    private Long tipoContrato;

    @Column(name= "facturacion_electronica")
    private Boolean facturacionElectronica;

    @Column(name= "subscripcion_activa")
    private Boolean subscripcionActiva;

    @Column(name="ultima_fecha_pago")
    private LocalDate ultimaFechaPago;

    @Column(name= "id_plan")
    private Long idPlan;

    @Column(name= "servicio_telefono")
    private Boolean servicioTelefono;

    @Column(name= "servicio_internet")
    private Boolean servicioInternet;

    @Column(name= "cargo_mensual")
    private BigDecimal cargoMensual;

    @Column(name= "cargos_totales")
    private BigDecimal cargosTotales;

    @Column(name= "fecha_alta_cliente")
    private LocalDateTime fechaAltaCliente;

    @Column(name = "fecha_alta_servicio")
    private LocalDateTime fechaAltaServicio;

    public Long getIdServicio() {
        return idServicio;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getGenero() {
        return genero;
    }

    public Integer getAdulto() {
        return adulto;
    }

    public Boolean getTienePareja() {
        return tienePareja;
    }

    public Boolean getTieneDependientes() {
        return tieneDependientes;
    }

    public Integer getAntiguedadmeses() {
        return antiguedadmeses;
    }

    public Long getTipoContrato() {
        return tipoContrato;
    }

    public Boolean getFacturacionElectronica() {
        return facturacionElectronica;
    }

    public Boolean getSubscripcionActiva() {
        return subscripcionActiva;
    }

    public LocalDate getUltimaFechaPago() {
        return ultimaFechaPago;
    }

    public Long getIdPlan() {
        return idPlan;
    }

    public Boolean getServicioTelefono() {
        return servicioTelefono;
    }

    public Boolean getServicioInternet() {
        return servicioInternet;
    }

    public BigDecimal getCargoMensual() {
        return cargoMensual;
    }

    public BigDecimal getCargosTotales() {
        return cargosTotales;
    }

    public LocalDateTime getFechaAltaCliente() {
        return fechaAltaCliente;
    }

    public LocalDateTime getFechaAltaServicio() {
        return fechaAltaServicio;
    }
}
