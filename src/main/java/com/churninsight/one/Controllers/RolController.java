package com.churninsight.one.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.churninsight.one.models.entities.rol.Rol;
import com.churninsight.one.services.RolService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Roles", description = "Endpoints de gestión de roles")
public class RolController {

    @Autowired
    private  RolService rolService;

    // public RolController(RolService rolService) {
    //     this.rolService = rolService;
    // }

    @Tag(name = "Listar roles", description = "Obtener lista de todos los roles")
    @GetMapping
    public ResponseEntity<List<Rol>> listarRoles() {
        return ResponseEntity.ok(rolService.listarRoles());
    }

    @Tag(name = "Obtener rol por ID", description = "Obtener un rol por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerRolPorId(@PathVariable Long id) {
        return rolService.obtenerRolPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Tag(name = "Crear rol", description = "Crear un nuevo rol")
    @PostMapping
    public ResponseEntity<Rol> crearRol(@RequestBody Rol rol) {
        try {
            Rol nuevoRol = rolService.crearRol(rol);
            return new ResponseEntity<>(nuevoRol, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Tag(name = "Actualizar rol", description = "Actualizar un rol existente")
    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizarRol(@PathVariable Long id, @RequestBody Rol rol) {
        try {
            Rol rolActualizado = rolService.actualizarRol(id, rol);
            return ResponseEntity.ok(rolActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Tag(name = "Eliminar rol", description = "Eliminar un rol por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        try {
            rolService.eliminarRol(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // @PostMapping("/{rolId}/permisos/{permisoId}")
    // public ResponseEntity<Rol> asignarPermiso(@PathVariable Long rolId, @PathVariable Long permisoId) {
    //     try {
    //         Rol rol = rolService.asignarPermiso(rolId, permisoId);
    //         return ResponseEntity.ok(rol);
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    // @DeleteMapping("/{rolId}/permisos/{permisoId}")
    // public ResponseEntity<Rol> removerPermiso(@PathVariable Long rolId, @PathVariable Long permisoId) {
    //     try {
    //         Rol rol = rolService.removerPermiso(rolId, permisoId);
    //         return ResponseEntity.ok(rol);
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.notFound().build();
    //     }
    // }
}
