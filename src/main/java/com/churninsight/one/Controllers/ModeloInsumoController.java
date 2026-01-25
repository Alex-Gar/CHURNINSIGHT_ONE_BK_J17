package com.churninsight.one.Controllers;

import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.services.ModeloInsumoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/modelo-insumos")
@Tag(name = "Modelo IA", description = "Endpoints para obtener datos formateados para el modelo de IA")
public class ModeloInsumoController {

    @Autowired
    private ModeloInsumoService service;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<ApiResponse> obtenerPorUsuario(@PathVariable String idUsuario) {
        ApiResponse response = service.obtenerInsumosPorUsuario(idUsuario);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
