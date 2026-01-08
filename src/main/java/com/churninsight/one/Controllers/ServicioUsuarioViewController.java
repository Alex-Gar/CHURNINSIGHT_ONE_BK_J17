package com.churninsight.one.Controllers;

import com.churninsight.one.models.entities.servicio.ServicioUsuarioView;
import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.services.ServicioUsuarioViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/servicios-usuarios")
public class ServicioUsuarioViewController {

    @Autowired
    private ServicioUsuarioViewService service;

    //Listar
    @GetMapping
    public ResponseEntity<ApiResponse>listar(
            @RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "10") Integer tamanio) {

        Page<ServicioUsuarioView> data = service.listar(pagina, tamanio);

        return ResponseEntity.ok(
                new ApiResponse(
                        data.getContent(),
                        "Lista obtenida correctamente",
                         true
                )
        );
    }

    //Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> buscarPorId(@PathVariable Long id){

        ServicioUsuarioView data = service.buscarPorId(id);

        return ResponseEntity.ok(
                new ApiResponse(
                        data,
                        "Servicio encontrado",
                        true
                )
        );
    }
}
