package com.churninsight.one.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.churninsight.one.models.entities.usuario.Usuario;
import com.churninsight.one.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> resultado = this.usuarioService.listar();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable String id) {
        Usuario resultado = this.usuarioService.buscarPorId(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> editarUsuarios(@Valid @PathVariable String id, @RequestBody Usuario usuario) {
        Usuario usuarioEditado = this.usuarioService.editar(usuario);
        return new ResponseEntity<>(usuarioEditado, HttpStatus.OK);
    }
}
