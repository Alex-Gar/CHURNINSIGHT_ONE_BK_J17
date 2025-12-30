package com.churninsight.one.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.churninsight.one.models.entities.usuario.Usuario;
import com.churninsight.one.services.UsuarioService;

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

    // @PutMapping("{id}")
    // public ResponseEntity<?> editarUsuarios(@Valid @PathVariable String id, @RequestBody Usuario usuario) {
    //     Usuario resultado = null;
    //     // try {
    //     //     if (this.existeId(usuario.getId()) && usuario.getId().equals(usuario.getId())) {
    //     //         resultado = this.editar(usuario);
    //     //         return new ResponseEntity<>(resultado, HttpStatus.OK);
    //     //     } else {
    //     //         throw new ResourceNotFoundException("usuario", "id", id);
    //     //     }
    //     // } catch (DataAccessException ex) {
    //     //     throw new BadRequestException(ex.getMessage());
    //     // }
    // }
}
