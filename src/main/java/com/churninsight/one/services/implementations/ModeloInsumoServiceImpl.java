package com.churninsight.one.services.implementations;

import com.churninsight.one.exceptions.ResourceNotFoundException;
import com.churninsight.one.models.entities.vistas.ModeloInsumoView;
import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.models.repositories.ModeloInsumoViewRepository;
import com.churninsight.one.services.ModeloInsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModeloInsumoServiceImpl implements ModeloInsumoService {

    @Autowired
    private ModeloInsumoViewRepository repository;

    @Override
    public ApiResponse obtenerInsumosPorUsuario(String idUsuario) {
        ModeloInsumoView data = repository.findByIdCliente(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("datos de modelo para el usuario", "id", idUsuario));

        return new ApiResponse("Datos formateados para el modelo obtenidos con éxito", true, data);
    }
}
