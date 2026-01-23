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

import com.churninsight.one.models.entities.oferta.Oferta;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Oferta o SET o.deletedAt = CURRENT_TIMESTAMP WHERE o.id = :id")
    Integer softDeleteById(@Param("id") Long id);

    @Query("SELECT o FROM Oferta o WHERE o.deletedAt IS NULL")
    Page<Oferta> findAllActive(Pageable pageable);

    @Query("SELECT o FROM Oferta o WHERE o.deletedAt IS NULL AND o.id = :id")
    Optional<Oferta> findActiveById(@Param("id") Long id);

    @Query("SELECT o FROM Oferta o WHERE o.deletedAt IS NOT NULL")
    Page<Oferta> findDeleted(Pageable pageable);

    @Query("SELECT o FROM Oferta o WHERE o.deletedAt IS NULL AND o.aplicaChurn = true AND o.activa = true")
    Page<Oferta> findActiveChurnOffers(Pageable pageable);
}
