package com.churninsight.one.models.peyload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrediccionResponseDTO {

    @JsonProperty("abandono_cliente")
    private Integer abandonoCliente;

    @JsonProperty("probabilidad_abandono")
    private Double probabilidadAbandono;

    public Integer getAbandonoCliente(){
        return abandonoCliente;
    }

    public void setAbandonoCliente(Integer abandonoCliente) {
        this.abandonoCliente = abandonoCliente;
    }

    public Double getProbabilidadAbandono(){
        return probabilidadAbandono;
    }

    public void setProbabilidadAbandono(Double probabilidadAbandono) {
        this.probabilidadAbandono = probabilidadAbandono;
    }

}
