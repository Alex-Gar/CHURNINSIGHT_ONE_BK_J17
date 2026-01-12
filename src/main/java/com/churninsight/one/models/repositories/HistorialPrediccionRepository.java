package com.churninsight.one.models.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.churninsight.one.models.entities.historialPredicciones.HistorialPrediccion;

@Repository
public interface HistorialPrediccionRepository extends JpaRepository<HistorialPrediccion, Long> {
    @Modifying
    @Transactional
    @Query("""
            UPDATE HistorialPrediccion u SET u.deletedAt = CURRENT_TIMESTAMP WHERE u.id = :id
            """)
    Integer softDeleteById(Long id);

    @Query("""
            SELECT u FROM HistorialPrediccion u WHERE u.deletedAt is NULL
            """)
    Page<HistorialPrediccion> findAllActiveHistorialPrediccions(Pageable pageable);

    @Query("""
                SELECT u FROM HistorialPrediccion u
                WHERE u.deletedAt IS NULL AND u.id = :id
            """)
    Optional<HistorialPrediccion> findActiveById(@Param("id") Long id);

    @Query("""
                SELECT u FROM HistorialPrediccion u
                WHERE u.deletedAt IS NOT NULL
            """)
    Page<HistorialPrediccion> findDeleted(Pageable pageable);
}
