package com.churninsight.one.Controllers;


import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.models.peyload.PrediccionRequestDTO;
import com.churninsight.one.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService= usuarioService;
    }

    @PostMapping("/predict")
    public ResponseEntity<ApiResponse> predecir(
           @RequestBody  PrediccionRequestDTO request) {

        return ResponseEntity.ok(
                usuarioService.predecirChurn(request));

    }
}
