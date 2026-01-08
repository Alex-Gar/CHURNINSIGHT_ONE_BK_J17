package com.churninsight.one.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.churninsight.one.models.entities.vwserviciosusuario.ServicioUsuarioView;
import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.services.ServicioUsuarioViewService;

@RestController
@RequestMapping("/api/servicios-usuarios")
public class ServicioUsuarioViewController {

        @Autowired
        private ServicioUsuarioViewService service;

        // Listar
        @GetMapping
        public ResponseEntity<ApiResponse> listar(
                        @RequestParam(defaultValue = "0") Integer pagina,
                        @RequestParam(defaultValue = "10") Integer tamanio) {

                Page<ServicioUsuarioView> data = service.listar(pagina, tamanio);

                return ResponseEntity.ok(
                                new ApiResponse(
                                                data.getContent(),
                                                "Lista obtenida correctamente",
                                                true));
        }

        // Buscar por ID
        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse> buscarPorId(@PathVariable String id) {

                ServicioUsuarioView data = service.buscarPorId(id);

                return ResponseEntity.ok(
                                new ApiResponse(
                                                data,
                                                "Servicio encontrado",
                                                true));
        }
}
