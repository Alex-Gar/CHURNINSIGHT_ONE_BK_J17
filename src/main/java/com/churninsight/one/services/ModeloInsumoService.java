package com.churninsight.one.services;

import com.churninsight.one.models.peyload.ApiResponse;

public interface ModeloInsumoService {
    ApiResponse obtenerInsumosPorUsuario(String idUsuario);
}
