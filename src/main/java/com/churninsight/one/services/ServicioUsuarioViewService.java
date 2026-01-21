package com.churninsight.one.services;

import org.springframework.data.domain.Page;

import com.churninsight.one.models.entities.vistas.ServicioUsuarioView;

public interface ServicioUsuarioViewService {

    Page<ServicioUsuarioView> listar(Integer pagina, Integer tamanio);

    ServicioUsuarioView buscarPorId(String id);
}
