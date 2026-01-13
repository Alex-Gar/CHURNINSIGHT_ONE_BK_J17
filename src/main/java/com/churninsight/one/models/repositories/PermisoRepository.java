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

import com.churninsight.one.models.entities.permiso.Permiso;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long> {
        Optional<Permiso> findByNombre(String nombre);

        @Modifying
        @Transactional
        @Query("""
                        UPDATE Permiso u SET u.deletedAt = CURRENT_TIMESTAMP WHERE u.id = :id
                        """)
        int softDeleteById(Long id);

        @Query("""
                        SELECT u FROM Permiso u WHERE u.deletedAt is NULL
                        """)
        Page<Permiso> findAllActivePermiso(Pageable pageable);

        @Query("""
                            SELECT u FROM Permiso u
                            WHERE u.deletedAt IS NULL AND u.id = :id
                        """)
        Optional<Permiso> findActiveById(@Param("id") Long id);

        @Query("""
                            SELECT u FROM Permiso u
                            WHERE u.deletedAt IS NOT NULL
                        """)
        Page<Permiso> findDeleted(Pageable pageable);
}
