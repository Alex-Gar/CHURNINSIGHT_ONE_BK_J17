package com.churninsight.one.services;

import org.springframework.data.domain.Page;

import com.churninsight.one.models.entities.historialPredicciones.HistorialPrediccion;
import com.churninsight.one.models.entities.historialPredicciones.HistorialPrediccionDto;
import com.churninsight.one.models.peyload.ApiResponse;

public interface HistorialPrediccionService {

    public Page<HistorialPrediccion> listar(Integer pagina, Integer tamanio);

    public ApiResponse buscarPorId(Long id);

    public ApiResponse crear(HistorialPrediccionDto historialPrediccionDto);

    public ApiResponse editar(HistorialPrediccionDto historialPrediccionDto);

    public Boolean existeId(Long id);

    public Page<HistorialPrediccion> listarHistorialActivos(Integer pagina, Integer tamanio);

    public Page<HistorialPrediccion> listarHistorialEliminados(Integer pagina, Integer tamanio);

    public ApiResponse buscarHistorialActivoPorId(Long id);

    public ApiResponse borradoLogico(Long id);

    public HistorialPrediccion guardarHistorialPrediccion(HistorialPrediccion historialPrediccion);

}
