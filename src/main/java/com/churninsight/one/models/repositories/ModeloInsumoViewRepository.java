package com.churninsight.one.models.repositories;

import com.churninsight.one.models.entities.vistas.ModeloInsumoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeloInsumoViewRepository extends JpaRepository<ModeloInsumoView, String> {
    Optional<ModeloInsumoView> findByIdCliente(String idCliente);
}
