package com.churninsight.one.models.repositories;

import com.churninsight.one.models.entities.prediccion.Prediccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PrediccionRepository extends JpaRepository<Prediccion, Long> {

    // Predicciones activas por usuario
    List<Prediccion> findByIdUsuarioAndDeletedAtIsNull(String idUsuario);

    // Todas las predicciones activas
    List<Prediccion> findAllByDeletedAtIsNull();

    @Query("""
            SELECT COUNT(p) FROM Prediccion p
            WHERE p.deletedAt IS NULL
            """)
    Long totalEvaluados();

    @Query("""
            SELECT COUNT(p) FROM Prediccion p
            WHERE p.deletedAt IS NULL AND p.churn = true
            """)
    Long totalChurn();

}
