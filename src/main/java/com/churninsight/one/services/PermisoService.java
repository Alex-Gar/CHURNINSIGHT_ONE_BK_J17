// package com.churninsight.one.services;

// import com.churninsight.one.models.entities.Permiso;
// import com.churninsight.one.models.repositories.PermisoRepository;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.List;
// import java.util.Optional;

// @Service
// @Transactional
// public class PermisoService {

//     private final PermisoRepository permisoRepository;

//     public PermisoService(PermisoRepository permisoRepository) {
//         this.permisoRepository = permisoRepository;
//     }

//     @Transactional(readOnly = true)
//     public List<Permiso> listarPermisos() {
//         return permisoRepository.findAll();
//     }

//     @Transactional(readOnly = true)
//     public Optional<Permiso> obtenerPermisoPorId(Long id) {
//         return permisoRepository.findById(id);
//     }

//     public Permiso crearPermiso(Permiso permiso) {
//         if (permisoRepository.findByNombre(permiso.getNombre()).isPresent()) {
//             throw new IllegalArgumentException("Ya existe un permiso con el nombre: " + permiso.getNombre());
//         }
//         return permisoRepository.save(permiso);
//     }

//     public Permiso actualizarPermiso(Long id, Permiso permisoDetalles) {
//         return permisoRepository.findById(id).map(permiso -> {
//             permiso.setNombre(permisoDetalles.getNombre());
//             permiso.setDescripcion(permisoDetalles.getDescripcion());
//             return permisoRepository.save(permiso);
//         }).orElseThrow(() -> new RuntimeException("Permiso no encontrado con id: " + id));
//     }

//     public void eliminarPermiso(Long id) {
//         if (!permisoRepository.existsById(id)) {
//             throw new RuntimeException("Permiso no encontrado con id: " + id);
//         }
//         permisoRepository.deleteById(id);
//     }
// }
