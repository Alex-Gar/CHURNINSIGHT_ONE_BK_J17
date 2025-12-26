package com.churninsight.one.models.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "id_cliente", unique=true)
    private String IdCliente;

    private String genero;
    private Boolean adulto_mayor;
    private Integer antiguedad_meses;
    private String tipo_contrato;
    private String metodo_pago;

    private Double cargo_mensual;
    private Double cargos_totales;

    private Integer abandono_cliente;
    private Double probabilidadAbandono;


    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(String IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Boolean getAdulto_mayor() {
        return adulto_mayor;
    }

    public void setAdulto_mayor(Boolean adulto_mayor) {
        this.adulto_mayor = adulto_mayor;
    }

    public Integer getAntiguedad_meses() {
        return antiguedad_meses;
    }

    public void setAntiguedad_meses(Integer antiguedad_meses) {
        this.antiguedad_meses = antiguedad_meses;
    }

    public String getTipo_contrato() {
        return tipo_contrato;
    }

    public void setTipo_contrato(String tipo_contrato) {
        this.tipo_contrato = tipo_contrato;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public Double getCargo_mensual() {
        return cargo_mensual;
    }

    public void setCargo_mensual(Double cargo_mensual) {
        this.cargo_mensual = cargo_mensual;
    }

    public Double getCargos_totales() {
        return cargos_totales;
    }

    public void setCargos_totales(Double cargos_totales) {
        this.cargos_totales = cargos_totales;
    }

    public Integer getAbandono_cliente() {
        return abandono_cliente;
    }

    public void setAbandono_cliente(Integer abandono_cliente) {
        this.abandono_cliente = abandono_cliente;
    }

    public Double getProbabilidadAbandono() {
        return probabilidadAbandono;
    }

    public void setProbabilidadAbandono(Double probabilidadAbandono) {
        this.probabilidadAbandono = probabilidadAbandono;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

}
