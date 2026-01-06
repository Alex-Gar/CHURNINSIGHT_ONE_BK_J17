package com.churninsight.one.Controllers;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // @GetMapping
    // public ResponseEntity<ApiResponse> listarUsuarios(@RequestParam(defaultValue
    // = "0") Integer pagina,
    // @RequestParam(defaultValue = "10") Integer tamanio) {
    // Page<Usuario> data = this.usuarioService.listar(pagina, tamanio);
    // ApiResponse response = new ApiResponse(data.get(), "Lista de usuariosobtenida con éxito", true);
    // return new ResponseEntity<>(response, HttpStatus.OK);
    // }

    // @GetMapping("{id}")Z
    // public ResponseEntity<ApiResponse> buscarUsuarioPorId(@PathVariable String
    // id) {
    // ApiResponse resultado = this.usuarioService.buscarPorId(id);
    // return new ResponseEntity<>(resultado, HttpStatus.OK);
    // }
    

    @GetMapping
    public ResponseEntity<ApiResponse> listarUsuariosActivos(@RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "10") Integer tamanio) {
        Page<Usuario> data = this.usuarioService.listarUsuariosActivos(pagina, tamanio);
        ApiResponse response = new ApiResponse(data.get(), "Lista de usuarios obtenida con éxito", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/eliminados")
    public ResponseEntity<ApiResponse> listarUsuariosEliminados(@RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "10") Integer tamanio) {
        Page<Usuario> data = this.usuarioService.listarUsuariosEliminados(pagina, tamanio);
        ApiResponse response = new ApiResponse(data.get(), "Lista de usuarios eliminados obtenida con éxito", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> buscarUsuarioPorId(@PathVariable String id) {
        ApiResponse resultado = this.usuarioService.buscarUsuarioActivoPorId(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> crearUsuario(@Valid @RequestBody UsuarioDto usuarioDto) {
        ApiResponse nuevoUsuario = this.usuarioService.crear(usuarioDto);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> editarUsuarios(@Valid @PathVariable String id, @RequestBody UsuarioDto usuarioDto) {
        ApiResponse usuarioEditado = this.usuarioService.editar(usuarioDto);
        return new ResponseEntity<>(usuarioEditado, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> eliminarUsuario(@PathVariable String id) {
        this.usuarioService.borradoLogico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
