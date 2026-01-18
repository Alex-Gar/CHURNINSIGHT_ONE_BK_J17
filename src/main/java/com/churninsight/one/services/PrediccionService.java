package com.churninsight.one.services;

import java.util.List;

import com.churninsight.one.models.entities.prediccion.Prediccion;

public interface PrediccionService {

    //1. Evaluar predicción (consume DS + guarda)
    Prediccion evaluarPrediccion(String idUsuario, Object requestDs);

    // 2. Listar predicciones activas por usuario
    List<Prediccion> listarPorUsuario( String idUsuario);

    //3. Borrado Lógico por usuario
    void eliminarLogicoPorUsuario(String idUsuario);

    //4. Listar todas las predicciones activas
    List<Prediccion> listarActivas();

    //5. estadísticas
    Long obtenerTotalEvaluados();
    Long obtenerTotalChurn();

}
