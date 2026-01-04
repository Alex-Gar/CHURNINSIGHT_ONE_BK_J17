package com.churninsight.one.models.cliente.dto;

public record DatosDsPredict(

        Boolean churn,
        String prevision,
        Double probabilidad

) {
}
