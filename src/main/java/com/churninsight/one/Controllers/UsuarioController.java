package com.churninsight.one.Controllers;


import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.models.peyload.PrediccionRequestDTO;
import com.churninsight.one.services.UsuarioServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioServices usuarioService;

    public UsuarioController(UsuarioServices usuarioService){
        this.usuarioService= usuarioService;
    }

    @PostMapping("/predict")
    public ResponseEntity<ApiResponse> predecir(
           @RequestBody  PrediccionRequestDTO request) {

        return ResponseEntity.ok(
                usuarioService.predecirChurn(request));

    }
}
