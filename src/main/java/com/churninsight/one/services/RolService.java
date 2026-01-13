package com.churninsight.one.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.churninsight.one.models.entities.rol.Rol;
import com.churninsight.one.models.repositories.PermisoRepository;
import com.churninsight.one.models.repositories.RolRepository;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PermisoRepository permisoRepository;

    // public RolService(RolRepository rolRepository, PermisoRepository
    // permisoRepository) {
    // this.rolRepository = rolRepository;
    // this.permisoRepository = permisoRepository;
    // }

    @Transactional(readOnly = true)
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Rol> obtenerRolPorId(Long id) {
        return rolRepository.findById(id);
    }

    public Rol crearRol(Rol rol) {
        if (rolRepository.findByNombre(rol.getNombre()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un rol con el nombre: " + rol.getNombre());
        }
        return rolRepository.save(rol);
    }

    public Rol actualizarRol(Long id, Rol rolDetalles) {
        return rolRepository.findById(id).map(rol -> {
            rol.setNombre(rolDetalles.getNombre());
            rol.setDescripcion(rolDetalles.getDescripcion());
            return rolRepository.save(rol);
        }).orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
    }

    public void eliminarRol(Long id) {
        if (!rolRepository.existsById(id)) {
            throw new RuntimeException("Rol no encontrado con id: " + id);
        }
        rolRepository.deleteById(id);
    }

    // public Rol asignarPermiso(Long rolId, Long permisoId) {
    // Rol rol = rolRepository.findById(rolId)
    // .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " +
    // rolId));

    // Permiso permiso = permisoRepository.findById(permisoId)
    // .orElseThrow(() -> new RuntimeException("Permiso no encontrado con id: " +
    // permisoId));

    // if (!rol.getPermisos().contains(permiso)) {
    // rol.getPermisos().add(permiso);
    // return rolRepository.save(rol);
    // }
    // return rol;
    // }

    // public Rol removerPermiso(Long rolId, Long permisoId) {
    // Rol rol = rolRepository.findById(rolId)
    // .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " +
    // rolId));

    // Permiso permiso = permisoRepository.findById(permisoId)
    // .orElseThrow(() -> new RuntimeException("Permiso no encontrado con id: " +
    // permisoId));

    // rol.getPermisos().remove(permiso);
    // return rolRepository.save(rol);
    // }
}
