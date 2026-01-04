package com.churninsight.one.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MetodoPago {

    ELECTRONIC_CHECK,
    BANK_TRANSFER,
    CREDIT_CARD;

    @JsonCreator
    public static final MetodoPago from(String data){
        if (data == null || data.isBlank()){
            return null;
        }
        return MetodoPago.valueOf(data.trim().toUpperCase().replace(" ", "_").replace("-", "_"));
    }

}
