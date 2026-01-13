package com.churninsight.one.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.churninsight.one.models.payload.LoginRequest;
import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.security.JwtUtils;
import com.churninsight.one.services.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación", description = "Endpoints de autenticación y registro")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils,
            UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.usuarioService = usuarioService;
    }

    @Tag(name = "Login", description = "Endpoint para autenticarse con email y contraseña")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.email(),
                    loginRequest.password());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            String token = jwtUtils.crearToken(authentication);

            return ResponseEntity.ok(new ApiResponse("Login exitoso", true, token));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new ApiResponse("Credenciales inválidas", false, null));
        }
    }

    @Tag(name = "Registro", description = "Endpoint para registrar un nuevo usuario")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(
            @RequestBody @Valid com.churninsight.one.models.entities.usuario.UsuarioDto usuarioDto) {
        return ResponseEntity.ok(usuarioService.crear(usuarioDto));
    }
}
