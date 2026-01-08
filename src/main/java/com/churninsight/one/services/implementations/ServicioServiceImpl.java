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
import com.churninsight.one.models.entities.servicio.Servicio;
import com.churninsight.one.models.entities.servicio.ServicioDto;
import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.models.repositories.ServicioRepository;
import com.churninsight.one.services.ServicioService;

@Service
public class ServicioServiceImpl implements ServicioService {

   @Autowired
    private ServicioRepository servicioRepository;

   //Listar servicios activos
    @Transactional(readOnly = true)
    @Override
    public Page<Servicio> listarServiciosActivos(Integer pagina, Integer tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<Servicio> resultado = this.servicioRepository.findAllActive(pageable);

        if (resultado == null || resultado.isEmpty()){
            throw new ResourceNotFoundException("servicios");
        }
        return resultado;
    }

    //Listar servicios eliminados
    @Transactional(readOnly = true)
    @Override
    public Page<Servicio> listarServiciosEliminados(Integer pagina, Integer tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<Servicio> resultado = this.servicioRepository.findDeleted(pageable);

        if(resultado == null || resultado.isEmpty()){
            throw new ResourceNotFoundException("servicios");
        }
        return resultado;
    }

    // Buscar servicio activo por id
    @Transactional(readOnly = true)
    @Override
    public ApiResponse buscarServicioActivoPorId(Long id) {
        Servicio resultado = this.servicioRepository.findActiveById(id).orElse(null);

        if (resultado == null){
            throw new ResourceNotFoundException("servicio", " id ", id);
        }
        return new ApiResponse("Servicio obtenido con éxito", true, resultado);

    }

    //Crear servicio
    @Transactional
    @Override
    public ApiResponse crear(ServicioDto servicioDto) {
        try{
            Servicio nuevoServicio = new Servicio();
            nuevoServicio.setIdPlan(servicioDto.idPlan());
            nuevoServicio.setUltimaFechaPago(servicioDto.ultimaFechaPago());
            nuevoServicio.setFacturacionElectronica(servicioDto.facturacionElectronica());
            nuevoServicio.setTipoContrato(servicioDto.tipoContrato());
            nuevoServicio.setIdUsuario(servicioDto.idUsuario());
            nuevoServicio.setSubscription(servicioDto.subscription());

            Servicio resultado = this.servicioRepository.save(nuevoServicio);

            return new ApiResponse("Servicio creado con éxito", true, resultado);

        }catch (DataAccessException ex){
            throw  new BadRequestException(ex.getMessage());
        }
    }

    //Editar servicio
    @Transactional
    @Override
    public ApiResponse editar(Long id, ServicioDto servicioDto) {
        try{
            Servicio servicio = this.servicioRepository.findById(id).orElse(null);

            if (servicio == null || servicio.getDeletedAt() != null){
                throw new ResourceNotFoundException("servicio", "id", id);
            }

            servicio.setIdPlan(servicioDto.idPlan());
            servicio.setUltimaFechaPago(servicioDto.ultimaFechaPago());
            servicio.setFacturacionElectronica(servicioDto.facturacionElectronica());
            servicio.setTipoContrato(servicioDto.tipoContrato());
            servicio.setIdUsuario(servicioDto.idUsuario());
            servicio.setSubscription(servicioDto.subscription());

            Servicio resultado = this.servicioRepository.save(servicio);

            return new ApiResponse("Servicio editado con éxito", true, resultado);
        }catch(DataAccessException ex){
            throw new BadRequestException(ex.getMessage());
        }

    }
 //Borrado Lógico
    @Override
    public ApiResponse borradoLogico(Long id) {
        Servicio servicio = this.servicioRepository.findById(id).orElse(null);

        if(servicio == null || servicio.getDeletedAt() !=null){
            throw new ResourceNotFoundException("servicio", "id", id);

        }
        this.servicioRepository.softDeleteById(id);
        return new ApiResponse("Servicio eliminado con éxito", true);
    }
}
