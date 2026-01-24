package com.churninsight.one.Controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.churninsight.one.exceptions.BadRequestException;
import com.churninsight.one.models.dto.request.AuthResponse;
import com.churninsight.one.models.peyload.LoginRequest;
import com.churninsight.one.services.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@Tag(name = "Autenticación", description = "Endpoints de autenticación y registro")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Tag(name = "Login", description = "Endpoint para autenticarse con email y contraseña")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            return new ResponseEntity<>(this.usuarioService.loginUsuario(loginRequest), HttpStatus.OK);

        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Tag(name = "Registro", description = "Endpoint para registrar un nuevo usuario")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody @Valid com.churninsight.one.models.entities.usuario.UsuarioDto usuarioDto) {
        try {
            return ResponseEntity.ok(usuarioService.crearUsuario(usuarioDto));
        } catch (DataAccessException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

}



















