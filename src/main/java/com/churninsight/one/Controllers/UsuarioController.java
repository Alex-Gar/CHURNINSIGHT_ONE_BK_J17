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

import com.churninsight.one.models.entities.usuario.Usuario;
import com.churninsight.one.models.entities.usuario.UsuarioDto;
import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.services.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Churn", description = "Endpoints de gestión de usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // @GetMapping
    // public ResponseEntity<ApiResponse> listarUsuarios(@RequestParam(defaultValue
    // = "0") Integer pagina,
    // @RequestParam(defaultValue = "10") Integer tamanio) {
    // Page<Usuario> data = this.usuarioService.listar(pagina, tamanio);
    // ApiResponse response = new ApiResponse(data.get(), "Lista de usuariosobtenida
    // con éxito", true);
    // return new ResponseEntity<>(response, HttpStatus.OK);
    // }

    // @GetMapping("{id}")Z
    // public ResponseEntity<ApiResponse> buscarUsuarioPorId(@PathVariable String
    // id) {
    // ApiResponse resultado = this.usuarioService.buscarPorId(id);
    // return new ResponseEntity<>(resultado, HttpStatus.OK);
    // }

    @Tag(name = "Listar usuarios Activos", description = "Endpoints para listar usuarios activos")
    @GetMapping
    public ResponseEntity<ApiResponse> listarUsuariosActivos(@RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "10") Integer tamanio) {
        Page<Usuario> data = this.usuarioService.listarUsuariosActivos(pagina, tamanio);
        ApiResponse response = new ApiResponse(data.get(), "Lista de usuarios obtenida con éxito", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Tag(name = "Listar eliminados", description = "Endpoint para listar usuarios eliminados (soft delete)")
    @GetMapping("/eliminados")
    public ResponseEntity<ApiResponse> listarUsuariosEliminados(@RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "10") Integer tamanio) {
        Page<Usuario> data = this.usuarioService.listarUsuariosEliminados(pagina, tamanio);
        ApiResponse response = new ApiResponse(data.get(), "Lista de usuarios eliminados obtenida con éxito", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Tag(name = "Buscar usuario por ID", description = "Endpoints para buscar usuario activo por ID")
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> buscarUsuarioPorId(@PathVariable String id) {
        ApiResponse resultado = this.usuarioService.buscarUsuarioActivoPorId(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

  
    @Tag(name = "Editar usuario", description = "Endpoint para editar usuario")
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> editarUsuarios(@Valid @PathVariable String id,
            @RequestBody UsuarioDto usuarioDto) {
        ApiResponse usuarioEditado = this.usuarioService.editar(usuarioDto);
        return new ResponseEntity<>(usuarioEditado, HttpStatus.OK);
    }

    @Tag(name = "Eliminar usuario", description = "Endpoint para eliminar usuario")
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> eliminarUsuario(@PathVariable String id) {
        this.usuarioService.borradoLogico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Tag(name = "Asignar rol a usuario", description = "Endpoint para asignar rol a usuario")
    @PostMapping("/{usuarioId}/roles/{rolId}")
    public ResponseEntity<ApiResponse> asignarRol(@PathVariable String usuarioId, @PathVariable Long rolId) {
        ApiResponse response = this.usuarioService.asignarRol(usuarioId, rolId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Tag(name = "Remover rol de usuario", description = "Endpoint para remover rol de usuario")
    @DeleteMapping("/{usuarioId}/roles/{rolId}")
    public ResponseEntity<ApiResponse> removerRol(@PathVariable String usuarioId, @PathVariable Long rolId) {
        ApiResponse response = this.usuarioService.removerRol(usuarioId, rolId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
