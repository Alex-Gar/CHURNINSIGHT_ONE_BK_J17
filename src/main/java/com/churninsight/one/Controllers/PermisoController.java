package com.churninsight.one.controllers;

import java.util.List;

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

import com.churninsight.one.models.entities.permiso.Permiso;
import com.churninsight.one.services.PermisoService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/permisos")
@Tag(name = "Permisos", description = "Endpoints de gestión de permisos")
public class PermisoController {

    private final PermisoService permisoService;

    public PermisoController(PermisoService permisoService) {
        this.permisoService = permisoService;
    }

    @Tag(name = "Listar permisos", description = "Obtener lista de todos los permisos")
    @GetMapping
    public ResponseEntity<List<Permiso>> listarPermisos() {
        return ResponseEntity.ok(permisoService.listarPermisos());
    }

    @Tag(name = "Obtener permiso por ID", description = "Obtener un permiso por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Permiso> obtenerPermisoPorId(@PathVariable Long id) {
        return permisoService.obtenerPermisoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Tag(name = "Crear permiso", description = "Crear un nuevo permiso")
    @PostMapping
    public ResponseEntity<Permiso> crearPermiso(@RequestBody Permiso permiso) {
        try {
            Permiso nuevoPermiso = permisoService.crearPermiso(permiso);
            return new ResponseEntity<>(nuevoPermiso, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Tag(name = "Actualizar permiso", description = "Actualizar un permiso existente")
    @PutMapping("/{id}")
    public ResponseEntity<Permiso> actualizarPermiso(@PathVariable Long id, @RequestBody Permiso permiso) {
        try {
            Permiso permisoActualizado = permisoService.actualizarPermiso(id, permiso);
            return ResponseEntity.ok(permisoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Tag(name = "Eliminar permiso", description = "Eliminar un permiso por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPermiso(@PathVariable Long id) {
        try {
            permisoService.eliminarPermiso(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
