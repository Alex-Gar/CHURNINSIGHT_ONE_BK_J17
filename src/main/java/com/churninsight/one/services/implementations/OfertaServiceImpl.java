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
import com.churninsight.one.models.entities.oferta.Oferta;
import com.churninsight.one.models.entities.oferta.OfertaDto;
import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.models.repositories.OfertaRepository;
import com.churninsight.one.services.OfertaService;

@Service
public class OfertaServiceImpl implements OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Override
    public Page<Oferta> listar(Integer pagina, Integer tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<Oferta> resultado = this.ofertaRepository.findAll(pageable);
        if (resultado == null || resultado.isEmpty()) {
            throw new ResourceNotFoundException("ofertas");
        }
        return resultado;
    }

    @Override
    public Page<Oferta> listarActivos(Integer pagina, Integer tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<Oferta> resultado = this.ofertaRepository.findAllActive(pageable);
        if (resultado == null || resultado.isEmpty()) {
            throw new ResourceNotFoundException("ofertas activas");
        }
        return resultado;
    }

    @Override
    public Page<Oferta> listarEliminados(Integer pagina, Integer tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<Oferta> resultado = this.ofertaRepository.findDeleted(pageable);
        if (resultado == null || resultado.isEmpty()) {
            throw new ResourceNotFoundException("ofertas eliminadas");
        }
        return resultado;
    }

    @Override
    public Page<Oferta> listarOfertasChurn(Integer pagina, Integer tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<Oferta> resultado = this.ofertaRepository.findActiveChurnOffers(pageable);
        if (resultado == null || resultado.isEmpty()) {
            throw new ResourceNotFoundException("ofertas de retención (churn)");
        }
        return resultado;
    }

    @Override
    public ApiResponse buscarPorId(Long id) {
        Oferta oferta = this.ofertaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("oferta", "id", id));
        return new ApiResponse("Oferta obtenida con éxito", true, oferta);
    }

    @Override
    public ApiResponse buscarActivoPorId(Long id) {
        Oferta oferta = this.ofertaRepository.findActiveById(id)
                .orElseThrow(() -> new ResourceNotFoundException("oferta activa", "id", id));
        return new ApiResponse("Oferta activa obtenida con éxito", true, oferta);
    }

    @Override
    @Transactional
    public ApiResponse crear(OfertaDto ofertaDto) {
        try {
            Oferta oferta = new Oferta();
            mapDtoToEntity(ofertaDto, oferta);
            Oferta guardada = this.ofertaRepository.save(oferta);
            return new ApiResponse("Oferta creada con éxito", true, guardada);
        } catch (DataAccessException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ApiResponse editar(Long id, OfertaDto ofertaDto) {
        try {
            Oferta oferta = this.ofertaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("oferta", "id", id));
            mapDtoToEntity(ofertaDto, oferta);
            Oferta actualizada = this.ofertaRepository.save(oferta);
            return new ApiResponse("Oferta actualizada con éxito", true, actualizada);
        } catch (DataAccessException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ApiResponse borradoLogico(Long id) {
        this.buscarActivoPorId(id);
        this.ofertaRepository.softDeleteById(id);
        return new ApiResponse("Oferta eliminada con éxito", true);
    }

    @Override
    public Boolean existeId(Long id) {
        return this.ofertaRepository.existsById(id);
    }

    private void mapDtoToEntity(OfertaDto dto, Oferta entity) {
        entity.setNombre(dto.nombre());
        entity.setDescripcion(dto.descripcion());
        entity.setDescuentoPorcentaje(dto.descuentoPorcentaje());
        entity.setDescuentoMonto(dto.descuentoMonto());
        entity.setDuracionMeses(dto.duracionMeses());
        entity.setAplicaChurn(dto.aplicaChurn());
        entity.setActiva(dto.activa());
    }
}
