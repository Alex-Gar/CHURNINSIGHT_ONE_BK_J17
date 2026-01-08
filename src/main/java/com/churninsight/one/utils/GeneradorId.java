package com.churninsight.one.utils;

import java.util.Random;

public class GeneradorId {
    private static final String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int longitudId = 5;

    public static String generarId() {
        return primeraParteAleatoria() + "-" + segundaParteAleatoria();
    }

    private static String primeraParteAleatoria() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(longitudId);

        for (int i = 0; i < longitudId - 1; i++) {
            int indice = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(indice));
        }

        return sb.toString();
    }

    private static String segundaParteAleatoria() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(longitudId);

        for (int i = 0; i < longitudId; i++) {
            int indice = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(indice));
        }

        return sb.toString();
    }
}