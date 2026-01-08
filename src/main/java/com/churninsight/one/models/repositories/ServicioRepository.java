package com.churninsight.one.models.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.churninsight.one.models.entities.servicio.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {

        @Modifying
        @Transactional
        @Query("""
                        UPDATE Servicio s SET s.deletedAt = CURRENT_TIMESTAMP WHERE s.id = :id
                        """)
        int softDeleteById(@Param("id") Long id);

        @Query("""
                        SELECT s FROM Servicio s WHERE s.deletedAt IS NULL
                        """)
        Page<Servicio> findAllActive(Pageable pageable);

        @Query("""
                        SELECT s FROM Servicio s WHERE s.deletedAt IS NULL AND s.id = :id
                        """)
        Optional<Servicio> findActiveById(@Param("id") Long id);

        @Query("""
                        SELECT s FROM Servicio s WHERE s.deletedAt IS NOT NULL
                        """)
        Page<Servicio> findDeleted(Pageable pageable);

}
