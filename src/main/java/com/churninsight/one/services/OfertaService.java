package com.churninsight.one.services;

import org.springframework.data.domain.Page;
import com.churninsight.one.models.entities.oferta.Oferta;
import com.churninsight.one.models.entities.oferta.OfertaDto;
import com.churninsight.one.models.peyload.ApiResponse;

public interface OfertaService {
    Page<Oferta> listar(Integer pagina, Integer tamanio);

    Page<Oferta> listarActivos(Integer pagina, Integer tamanio);

    Page<Oferta> listarEliminados(Integer pagina, Integer tamanio);

    Page<Oferta> listarOfertasChurn(Integer pagina, Integer tamanio);

    ApiResponse buscarPorId(Long id);

    ApiResponse buscarActivoPorId(Long id);

    ApiResponse crear(OfertaDto ofertaDto);

    ApiResponse editar(Long id, OfertaDto ofertaDto);

    ApiResponse borradoLogico(Long id);

    Boolean existeId(Long id);
}
