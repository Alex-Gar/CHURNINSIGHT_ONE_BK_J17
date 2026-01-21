package com.churninsight.one.models.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.churninsight.one.models.entities.prediccion.Prediccion;

public interface PrediccionRepository extends JpaRepository<Prediccion, Long> {

        @Query("""
                        SELECT u FROM Prediccion u
                        WHERE u.deletedAt IS NULL AND u.idUsuario = :id
                        """)
        Optional<Prediccion> findActiveByIdUsuario(@Param("id") String id);

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
