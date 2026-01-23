package com.churninsight.one.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.churninsight.one.models.dto.prediccion.PrediccionResponse;
import com.churninsight.one.models.entities.prediccion.Prediccion;
import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.services.PrediccionService;

@RestController
@RequestMapping("/api/predicciones")
public class PrediccionController {

        @Autowired
        private PrediccionService prediccionService;

        @PostMapping("/evaluar/{idUsuario}")
        public ResponseEntity<ApiResponse> evaluar(@PathVariable String idUsuario) {
                Prediccion prediccion = prediccionService.evaluarPrediccion(idUsuario);
                ApiResponse response = new ApiResponse(prediccion, "Predicción evaluada con éxito", true);
                return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // 2. GET -> Listar predicciones activas por usuario
        @GetMapping
        public ResponseEntity<List<PrediccionResponse>> listarActivas() {
                List<PrediccionResponse> response = prediccionService.listarActivas()
                                .stream()
                                .map(p -> new PrediccionResponse(
                                                p.getId(),
                                                p.getIdUsuario(),
                                                p.getChurn(),
                                                p.getPrevision(),
                                                p.getProbabilidad(),
                                                p.getCreatedAt()))
                                .toList();
                return ResponseEntity.ok(response);
        }

        // 5. GET -> Estadísticas
        @GetMapping("/estadisticas/conteo")
        public ResponseEntity<Map<String, Long>> obtenerEstadisticas() {
                Map<String, Long> stats = new HashMap<>();
                stats.put("totalEvaluados", prediccionService.obtenerTotalEvaluados());
                stats.put("totalChurn", prediccionService.obtenerTotalChurn());
                return ResponseEntity.ok(stats);
        }
}
