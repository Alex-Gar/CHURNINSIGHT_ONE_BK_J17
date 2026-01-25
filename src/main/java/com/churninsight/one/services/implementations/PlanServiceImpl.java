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
import com.churninsight.one.models.entities.plan.Plan;
import com.churninsight.one.models.entities.plan.PlanDto;
import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.models.repositories.PlanRepository;
import com.churninsight.one.services.PlanService;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Override
    public Page<Plan> listar(Integer pagina, Integer tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<Plan> resultado = this.planRepository.findAll(pageable);
        if (resultado == null || resultado.isEmpty()) {
            throw new ResourceNotFoundException("planes");
        }
        return resultado;
    }

    @Override
    public Page<Plan> listarActivos(Integer pagina, Integer tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<Plan> resultado = this.planRepository.findAllActive(pageable);
        if (resultado == null || resultado.isEmpty()) {
            throw new ResourceNotFoundException("planes activos");
        }
        return resultado;
    }

    @Override
    public Page<Plan> listarEliminados(Integer pagina, Integer tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<Plan> resultado = this.planRepository.findDeleted(pageable);
        if (resultado == null || resultado.isEmpty()) {
            throw new ResourceNotFoundException("planes eliminados");
        }
        return resultado;
    }

    @Override
    public ApiResponse buscarPorId(Long id) {
        Plan plan = this.planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("plan", "id", id));
        return new ApiResponse("Plan obtenido con éxito", true, plan);
    }

    @Override
    public ApiResponse buscarActivoPorId(Long id) {
        Plan plan = this.planRepository.findActiveById(id)
                .orElseThrow(() -> new ResourceNotFoundException("plan activo", "id", id));
        return new ApiResponse("Plan activo obtenido con éxito", true, plan);
    }

    @Override
    @Transactional
    public ApiResponse crear(PlanDto planDto) {
        try {
            Plan plan = new Plan();
            mapDtoToEntity(planDto, plan);
            Plan guardado = this.planRepository.save(plan);
            return new ApiResponse("Plan creado con éxito", true, guardado);
        } catch (DataAccessException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ApiResponse editar(Long id, PlanDto planDto) {
        try {
            Plan plan = this.planRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("plan", "id", id));
            mapDtoToEntity(planDto, plan);
            Plan actualizado = this.planRepository.save(plan);
            return new ApiResponse("Plan actualizado con éxito", true, actualizado);
        } catch (DataAccessException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ApiResponse borradoLogico(Long id) {
        this.buscarActivoPorId(id);
        this.planRepository.softDeleteById(id);
        return new ApiResponse("Plan eliminado con éxito", true);
    }

    @Override
    public Boolean existeId(Long id) {
        return this.planRepository.existsById(id);
    }

    private void mapDtoToEntity(PlanDto dto, Plan entity) {
        entity.setServicioTelefono(dto.servicioTelefono());
        entity.setServicioInternet(dto.servicioInternet());
        entity.setSeguridadEnLinea(dto.seguridadEnLinea());
        entity.setRespaldoEnLinea(dto.respaldoEnLinea());
        entity.setProteccionDispositivo(dto.proteccionDispositivo());
        entity.setSoporteTecnico(dto.soporteTecnico());
        entity.setLineasMultiples(dto.lineasMultiples());
        entity.setStreamingTv(dto.streamingTv());
        entity.setStreamingPeliculas(dto.streamingPeliculas());
        entity.setCargoMensual(dto.cargoMensual());
    }
}
