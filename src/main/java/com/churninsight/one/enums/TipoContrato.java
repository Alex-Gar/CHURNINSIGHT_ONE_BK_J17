package com.churninsight.one.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoContrato {

    MONTH_TO_MONTH,
    ONE_YEAR,
    TWO_YEAR;

    @JsonCreator
    public static final TipoContrato from(String data) {
        if (data == null || data.isBlank()){
            return null;
        }
        return TipoContrato.valueOf(data.trim().toUpperCase().replace(" ", "_").replace("-", "_"));
    }

}
