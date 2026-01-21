package com.churninsight.one.controllers;

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

import com.churninsight.one.models.entities.historialPredicciones.HistorialPrediccion;
import com.churninsight.one.models.entities.historialPredicciones.HistorialPrediccionDto;
import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.services.HistorialPrediccionService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/historialPredicciones")
@Tag(name = "Historial Predicciones", description = "Endpoints de gestión de historial de predicciones")
public class HistorialPrediccionController {

    @Autowired
    private HistorialPrediccionService historialPrediccionService;

    @Tag(name = "Listar usuarios Activos", description = "Endpoints para listar usuarios activos")
    @GetMapping
    public ResponseEntity<ApiResponse> listarUsuariosActivos(@RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "10") Integer tamanio) {
        Page<HistorialPrediccion> data = this.historialPrediccionService.listarHistorialActivos(pagina, tamanio);
        ApiResponse response = new ApiResponse(data.get(), "Lista del historial de predicciones obtenida con éxito",
                true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Tag(name = "Listar eliminados", description = "Endpoint para listar el hoistorial eliminados (soft delete)")
    @GetMapping("/eliminados")
    public ResponseEntity<ApiResponse> listarUsuariosEliminados(@RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "10") Integer tamanio) {
        Page<HistorialPrediccion> data = this.historialPrediccionService.listarHistorialEliminados(pagina, tamanio);
        ApiResponse response = new ApiResponse(data.get(),
                "Lista del historial de predicciones eliminadas obtenida con éxito", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Tag(name = "Buscar historial de prediccion por ID", description = "Endpoints para buscar usuario activo por ID")
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> buscarHistorialPorId(@PathVariable Long id) {
        ApiResponse resultado = this.historialPrediccionService.buscarHistorialActivoPorId(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Tag(name = "Crear historial", description = "Endpoints para crear historial")
    @PostMapping
    public ResponseEntity<ApiResponse> crearHistorial(
            @Valid @RequestBody HistorialPrediccionDto historialPrediccionDto) {
        ApiResponse nuevoHistorial = this.historialPrediccionService.crear(historialPrediccionDto);
        return new ResponseEntity<>(nuevoHistorial, HttpStatus.CREATED);
    }

    @Tag(name = "Editar historial", description = "Endpoint para editar historial")
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> editarHistorial(@Valid @PathVariable Long id,
            @RequestBody HistorialPrediccionDto historialPrediccionDto) {
        ApiResponse historialEditado = this.historialPrediccionService.editar(historialPrediccionDto);
        return new ResponseEntity<>(historialEditado, HttpStatus.OK);
    }

    @Tag(name = "Eliminar historial", description = "Endpoint para eliminar historial")
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> eliminarHistorial(@PathVariable Long id) {
        this.historialPrediccionService.borradoLogico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
