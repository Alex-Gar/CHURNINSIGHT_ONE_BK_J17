package com.churninsight.one.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.churninsight.one.models.dto.prediccion.PrediccionRequest;
import com.churninsight.one.models.dto.prediccion.PrediccionResponse;
import com.churninsight.one.models.entities.prediccion.Prediccion;
import com.churninsight.one.services.PrediccionService;

@RestController
@RequestMapping("/api/predicciones")
public class PrediccionController {

        // private final PrediccionService prediccionService;
        @Autowired
        private PrediccionService prediccionService;

        // public PrediccionController(PrediccionService prediccionService) {
        // this.prediccionService = prediccionService;
        // }

        // 1. POST -> evaluar predicción( consume DS + guarda)
        @PostMapping("/evaluar")
        public ResponseEntity<PrediccionResponse> evaluar(
                        @RequestBody PrediccionRequest request) {
                Prediccion prediccion = prediccionService.evaluarPrediccion(
                                request.idUsuario(),
                                request

                );
                return ResponseEntity.ok(
                                new PrediccionResponse(
                                                prediccion.getId(),
                                                prediccion.getIdUsuario(),
                                                prediccion.getChurn(),
                                                prediccion.getPrevision(),
                                                prediccion.getProbabilidad(),
                                                prediccion.getCreatedAt()

                                ));
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

        // 3. GET -> listar predicciones por usuario
        @GetMapping("/usuario/{idUsuario}")
        public ResponseEntity<List<PrediccionResponse>> listar(
                        @PathVariable String idUsuario) {
                List<PrediccionResponse> response = prediccionService
                                .listarPorUsuario(idUsuario)
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

        // 4. DELETE -> Borrado lógico por usuario
        @DeleteMapping("/usuario/{idUsuario}")
        public ResponseEntity<Void> eliminar(@PathVariable String idUsuario) {
                prediccionService.eliminarLogicoPorUsuario(idUsuario);
                return ResponseEntity.noContent().build();
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
