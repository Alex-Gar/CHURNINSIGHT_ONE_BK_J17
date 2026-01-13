package com.churninsight.one.services;

import com.churninsight.one.models.entities.prediccion.Prediccion;

import java.util.List;

public interface PrediccionService {

    Prediccion evaluarPrediccion(String idUsuario, Object requestDs);

    // 2. Buscar predicciones activas por usuario
    List<Prediccion> buscarPorUsuario(String idUsuario);

    void eliminarLogicoPorUsuario(String idUsuario);

    List<Prediccion> listarActivas();

    Long obtenerTotalEvaluados();

    Long obtenerTotalChurn();

}
