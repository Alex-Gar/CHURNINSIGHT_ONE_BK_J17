package com.churninsight.one.models.peyload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrediccionRequestDTO {

    @JsonProperty("id_cliente")
    private String IdCliente;

    private String genero;

    @JsonProperty("antiguedad_meses")
    private Integer antiguedadMeses;


    @JsonProperty("tipo_contrato")
    private String tipoContrato;

    public String getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(String idCliente) {
        this.IdCliente = idCliente;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getAntiguedadMeses() {
        return antiguedadMeses;
    }

    public void setAntiguedadMeses(Integer antiguedadMeses) {
        this.antiguedadMeses = antiguedadMeses;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
}
