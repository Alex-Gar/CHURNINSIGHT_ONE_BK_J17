package com.churninsight.one.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.churninsight.one.exceptions.BadRequestException;
import com.churninsight.one.exceptions.ResourceNotFoundException;
import com.churninsight.one.models.entities.historialPredicciones.HistorialPrediccion;
import com.churninsight.one.models.entities.historialPredicciones.HistorialPrediccionDto;
import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.models.repositories.HistorialPrediccionRepository;
import com.churninsight.one.services.HistorialPrediccionService;

@Service
public class HistorialPrediccionServiceImpl implements HistorialPrediccionService {

    @Autowired
    private HistorialPrediccionRepository historialPrediccionRepository;

    @Override
    public Page<HistorialPrediccion> listar(Integer pagina, Integer tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<HistorialPrediccion> resultado = this.historialPrediccionRepository.findAll(pageable);
        if (resultado == null || resultado.isEmpty()) {
            throw new ResourceNotFoundException("usuarios");
        }
        return resultado;
    }

    @Override
    public ApiResponse buscarPorId(Long id) {
        HistorialPrediccion resultado = this.historialPrediccionRepository.findById(id).orElse(null);
        if (resultado == null) {
            throw new ResourceNotFoundException("historialPrediccion", "id", id);
        }
        ApiResponse response = new ApiResponse("HistorialPrediccion obtenido con éxito", true, resultado);
        return response;
    }

    @Override
    public ApiResponse crear(HistorialPrediccionDto historialPrediccionDto) {
        try {
            HistorialPrediccion nuevoHistorialPrediccion = new HistorialPrediccion();
            nuevoHistorialPrediccion.setChurn(historialPrediccionDto.churn());
            nuevoHistorialPrediccion.setPrevision(historialPrediccionDto.prevision());
            nuevoHistorialPrediccion.setProbabilidad(historialPrediccionDto.probabilidad());

            HistorialPrediccion resultado = this.guardarHistorialPrediccion(nuevoHistorialPrediccion);
            System.out.println("ID: " + nuevoHistorialPrediccion.getId());
            ApiResponse response = new ApiResponse("HistorialPrediccion creado con éxito", true, resultado);
            return response;
        } catch (DataAccessException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Override
    public ApiResponse editar(HistorialPrediccionDto historialPrediccionDto) {
        try {
            if (this.existeId(historialPrediccionDto.id())
                    && historialPrediccionDto.id().equals(historialPrediccionDto.id())) {
                HistorialPrediccion historialPrediccion = this.historialPrediccionRepository
                        .findById(historialPrediccionDto.id()).orElse(null);
                if (historialPrediccion != null) {
                    historialPrediccion.setChurn(historialPrediccionDto.churn());
                    historialPrediccion.setPrevision(historialPrediccionDto.prevision());
                    historialPrediccion.setProbabilidad(historialPrediccionDto.probabilidad());

                    HistorialPrediccion resultado = this.guardarHistorialPrediccion(historialPrediccion);
                    ApiResponse response = new ApiResponse("Historial Predicción editado con éxito", true, resultado);
                    return response;
                } else {
                    throw new ResourceNotFoundException("historial predicción", "id", historialPrediccionDto.id());
                }
            } else {
                throw new ResourceNotFoundException("historial predicción", "id", historialPrediccionDto.id());
            }

        } catch (DataAccessException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Override
    public Boolean existeId(Long id) {
        return this.historialPrediccionRepository.existsById(id);

    }

    @Override
    public Page<HistorialPrediccion> listarHistorialActivos(Integer pagina, Integer tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<HistorialPrediccion> resultado = this.historialPrediccionRepository
                .findAllActiveHistorialPrediccions(pageable);
        if (resultado == null || resultado.isEmpty()) {
            throw new ResourceNotFoundException("historiales predicción");
        }
        return resultado;
    }

    @Override
    public Page<HistorialPrediccion> listarHistorialEliminados(Integer pagina, Integer tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<HistorialPrediccion> resultado = this.historialPrediccionRepository.findDeleted(pageable);
        if (resultado == null || resultado.isEmpty()) {
            throw new ResourceNotFoundException("historiales predicción");
        }
        return resultado;
    }

    @Override
    public ApiResponse buscarHistorialActivoPorId(Long id) {
        HistorialPrediccion resultado = this.historialPrediccionRepository.findActiveById(id).orElse(null);
        if (resultado == null) {
            throw new ResourceNotFoundException("historial predicción", "id", id);
        }
        ApiResponse response = new ApiResponse("Historial Predicción obtenido con éxito", true, resultado);
        return response;
    }

    @Transactional
    @Override
    public ApiResponse borradoLogico(Long id) {
        ApiResponse existeUsuario = this.buscarHistorialActivoPorId(id);
        if (existeUsuario.getData() == null) {
            throw new ResourceNotFoundException("Usuario", "id", id);
        } else {
            this.historialPrediccionRepository.softDeleteById(id);
            ApiResponse response = new ApiResponse("Usuario eliminado con éxito", true);
            return response;
        }
    }

    @Override
    public HistorialPrediccion guardarHistorialPrediccion(HistorialPrediccion historialPrediccion) {
        return this.historialPrediccionRepository.save(historialPrediccion);
    }

}
