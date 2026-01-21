package com.churninsight.one.utils;

public final class BooleanUtils {

    private BooleanUtils() {

    }

    public static String siNo(Boolean valor) {
        return Boolean.TRUE.equals(valor) ? "Sí" : "No";
    }
}
