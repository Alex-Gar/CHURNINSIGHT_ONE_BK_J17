package com.churninsight.one.services;

import com.churninsight.one.models.entities.servicio.ServicioUsuarioView;
import org.springframework.data.domain.Page;

public interface ServicioUsuarioViewService {

    Page<ServicioUsuarioView> listar(Integer pagina, Integer tamanio);

    ServicioUsuarioView buscarPorId(Long id);
}
