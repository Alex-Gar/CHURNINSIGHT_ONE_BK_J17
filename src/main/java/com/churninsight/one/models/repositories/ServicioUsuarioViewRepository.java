package com.churninsight.one.models.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.churninsight.one.models.entities.vistas.ServicioUsuarioView;

public interface ServicioUsuarioViewRepository extends JpaRepository<ServicioUsuarioView, String> {

    @Override
    Page<ServicioUsuarioView> findAll(Pageable pageable);

    Optional<ServicioUsuarioView> findByIdCliente(String idServicio);
}
