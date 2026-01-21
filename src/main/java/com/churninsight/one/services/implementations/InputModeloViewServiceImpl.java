package com.churninsight.one.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.churninsight.one.exceptions.ResourceNotFoundException;
import com.churninsight.one.models.entities.vistas.outputModeloView;
import com.churninsight.one.models.repositories.InputModeloViewRepository;
import com.churninsight.one.services.InputModeloViewService;

@Service
public class InputModeloViewServiceImpl implements InputModeloViewService {

    @Autowired
    private InputModeloViewRepository inputModeloViewRepository;

    @Override
    public outputModeloView buscarPorId(String id) {
        return inputModeloViewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Informacion del usuario", "id", id));
    }

}
