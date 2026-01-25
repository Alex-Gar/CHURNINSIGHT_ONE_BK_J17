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

import com.churninsight.one.models.entities.plan.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Plan p SET p.deletedAt = CURRENT_TIMESTAMP WHERE p.id = :id")
    Integer softDeleteById(@Param("id") Long id);

    @Query("SELECT p FROM Plan p WHERE p.deletedAt IS NULL")
    Page<Plan> findAllActive(Pageable pageable);

    @Query("SELECT p FROM Plan p WHERE p.deletedAt IS NULL AND p.id = :id")
    Optional<Plan> findActiveById(@Param("id") Long id);

    @Query("SELECT p FROM Plan p WHERE p.deletedAt IS NOT NULL")
    Page<Plan> findDeleted(Pageable pageable);
}
