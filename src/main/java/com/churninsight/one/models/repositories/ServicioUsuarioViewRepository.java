package com.churninsight.one.models.repositories;

import com.churninsight.one.models.entities.servicio.ServicioUsuarioView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicioUsuarioViewRepository  extends JpaRepository<ServicioUsuarioView, Long> {

    @Override
    Page<ServicioUsuarioView> findAll(Pageable pageable);

    Optional<ServicioUsuarioView> findByIdServicio(Long idServicio);
}
