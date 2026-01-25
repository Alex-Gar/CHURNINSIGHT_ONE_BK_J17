package com.churninsight.one.services;

import org.springframework.data.domain.Page;
import com.churninsight.one.models.entities.plan.Plan;
import com.churninsight.one.models.entities.plan.PlanDto;
import com.churninsight.one.models.peyload.ApiResponse;

public interface PlanService {
    Page<Plan> listar(Integer pagina, Integer tamanio);

    Page<Plan> listarActivos(Integer pagina, Integer tamanio);

    Page<Plan> listarEliminados(Integer pagina, Integer tamanio);

    ApiResponse buscarPorId(Long id);

    ApiResponse buscarActivoPorId(Long id);

    ApiResponse crear(PlanDto planDto);

    ApiResponse editar(Long id, PlanDto planDto);

    ApiResponse borradoLogico(Long id);

    Boolean existeId(Long id);
}
