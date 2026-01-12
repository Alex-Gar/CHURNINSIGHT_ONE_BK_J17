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

import com.churninsight.one.models.entities.rol.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
        Optional<Rol> findByNombre(String nombre);

        @Modifying
        @Transactional
        @Query("""
                        UPDATE Rol u SET u.deletedAt = CURRENT_TIMESTAMP WHERE u.id = :id
                        """)
        Integer softDeleteById(Long id);

        @Query("""
                        SELECT u FROM Rol u WHERE u.deletedAt is NULL
                        """)
        Page<Rol> findAllActiveRoles(Pageable pageable);

        @Query("""
                            SELECT u FROM Rol u
                            WHERE u.deletedAt IS NULL AND u.id = :id
                        """)
        Optional<Rol> findActiveById(@Param("id") Long id);

        @Query("""
                            SELECT u FROM Rol u
                            WHERE u.deletedAt IS NOT NULL
                        """)
        Page<Rol> findDeleted(Pageable pageable);
}
