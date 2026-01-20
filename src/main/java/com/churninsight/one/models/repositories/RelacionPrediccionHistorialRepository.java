package com.churninsight.one.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.churninsight.one.models.entities.relacionHistorial.RelacionPrediccionHistorial;

@Repository
public interface RelacionPrediccionHistorialRepository extends JpaRepository<RelacionPrediccionHistorial, Long> {

}
