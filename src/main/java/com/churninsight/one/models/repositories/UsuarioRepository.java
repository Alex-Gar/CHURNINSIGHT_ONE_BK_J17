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

import com.churninsight.one.models.entities.usuario.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Modifying
    @Transactional
    @Query("""
            UPDATE Usuario u SET u.deletedAt = CURRENT_TIMESTAMP WHERE u.id = :id
            """)
    int softDeleteById(String id);

    @Query("""
            SELECT u FROM Usuario u WHERE u.deletedAt is NULL
            """)
    Page<Usuario> findAllActiveUsuarios(Pageable pageable);

    @Query("""
        SELECT u FROM Usuario u
        WHERE u.deletedAt IS NULL AND u.id = :id
    """)
    Optional<Usuario> findActiveById(@Param("id") String id);

    @Query("""
        SELECT u FROM Usuario u
        WHERE u.deletedAt IS NOT NULL
    """)
    Page<Usuario> findDeleted(Pageable pageable);
}
