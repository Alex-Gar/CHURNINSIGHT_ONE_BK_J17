package com.churninsight.one.services;

import org.springframework.data.domain.Page;

import com.churninsight.one.models.entities.servicio.Servicio;
import com.churninsight.one.models.entities.servicio.ServicioDto;
import com.churninsight.one.models.peyload.ApiResponse;

public interface ServicioService {

    public Page<Servicio> listarServiciosActivos(Integer pagina, Integer tamanio);

    public Page<Servicio> listarServiciosEliminados(Integer pagina, Integer tamanio);

    public ApiResponse buscarServicioActivoPorId(Long id);

    public ApiResponse crear(ServicioDto servicioDto);

    public ApiResponse editar(Long id,  ServicioDto servicioDto);

    public ApiResponse borradoLogico(Long id);

}
