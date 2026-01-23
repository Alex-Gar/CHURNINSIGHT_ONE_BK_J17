package com.churninsight.one.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.churninsight.one.models.entities.servicio.Servicio;
import com.churninsight.one.models.entities.servicio.ServicioDto;
import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.services.ServicioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    //Listar servicios activos
    @GetMapping
    public ResponseEntity<ApiResponse> listarServiciosActivos(
            @RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "10") Integer tamanio ) {
        Page<Servicio> data = this.servicioService.listarServiciosActivos(pagina, tamanio);
        ApiResponse response = new ApiResponse(
                data.getContent(),
                "Lista de servicios obtenida con éxito",
                true
        );
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    //Listar servicios eliminados
    @GetMapping("/eliminados")
    public ResponseEntity<ApiResponse> listarServiciosEliminados(
            @RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "10") Integer tamanio ){

        Page<Servicio> data = this.servicioService.listarServiciosEliminados(pagina, tamanio);
        ApiResponse response = new ApiResponse(
                data.getContent(),
                "Lista de servicios eliminados obtenida ccon éxito",
                true
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Buscar servicio activo por ID
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> buscarServicioPorId(@PathVariable Long id){
        ApiResponse resultado = this.servicioService.buscarServicioActivoPorId(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    //Crear servicio
    @PostMapping
    public ResponseEntity<ApiResponse> crearServicio(
            @Valid @RequestBody ServicioDto servicioDto){

        ApiResponse nuevoServicio = this.servicioService.crear(servicioDto);
        return new ResponseEntity<>(nuevoServicio, HttpStatus.OK);
    }

    //Editar servicio
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> editarServicio(
            @PathVariable Long id,
            @Valid @RequestBody ServicioDto servicioDto){
        ApiResponse servicioEditado = this.servicioService.editar(id, servicioDto);
        return new ResponseEntity<>(servicioEditado, HttpStatus.OK);

    }

    //Borrado Lógico
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> eliminarServicio(@PathVariable Long id){
        this.servicioService.borradoLogico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
