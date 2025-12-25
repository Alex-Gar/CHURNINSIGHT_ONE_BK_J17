package com.churninsight.one.services;

import com.churninsight.one.models.entities.Rol;
import com.churninsight.one.models.repositories.RolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

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
}
