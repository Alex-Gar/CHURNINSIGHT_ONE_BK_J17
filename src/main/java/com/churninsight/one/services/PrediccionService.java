package com.churninsight.one.services;

import java.util.List;
import java.util.Optional;

import com.churninsight.one.models.dto.prediccion.PrediccionDatosPersonalizadosDTO;
import com.churninsight.one.models.entities.prediccion.Prediccion;

public interface PrediccionService {

Optional<Prediccion> buscarUsuarioPorId(String idUsuario);

    // 1. Evaluar predicción (consume DS + guarda)
    Prediccion evaluarPrediccion(String idUsuario);

    // 1.1 Evaluar predicción con datos personalizados
    Prediccion evaluarPrediccionConDatos(PrediccionDatosPersonalizadosDTO datosPersonalizados);

    // 4. Listar todas las predicciones activas
    List<Prediccion> listarActivas();

    // 5. estadísticas
    Long obtenerTotalEvaluados();

    Long obtenerTotalChurn();

}
