package com.churninsight.one.services.implementations;

import com.churninsight.one.models.entities.servicio.ServicioUsuarioView;
import com.churninsight.one.models.repositories.ServicioUsuarioViewRepository;
import com.churninsight.one.services.ServicioUsuarioViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuarioViewServiceImpl  implements ServicioUsuarioViewService {

    @Autowired
    private ServicioUsuarioViewRepository repository;

    @Override
    public Page<ServicioUsuarioView> listar(Integer pagina, Integer tamanio) {
        Pageable pageable= PageRequest.of(pagina, tamanio);
        return repository.findAll(pageable);
    }

    @Override
    public ServicioUsuarioView buscarPorId(Long id) {
        return repository.findByIdServicio(id)
                .orElseThrow(()->
                        new RuntimeException("Servicio no encontrado"));
    }
}
