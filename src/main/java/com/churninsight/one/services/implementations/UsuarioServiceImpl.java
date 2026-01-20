package com.churninsight.one.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.churninsight.one.exceptions.BadRequestException;
import com.churninsight.one.exceptions.ResourceNotFoundException;
import com.churninsight.one.models.entities.usuario.Usuario;
import com.churninsight.one.models.entities.usuario.UsuarioDto;
import com.churninsight.one.models.entities.rol.Rol;
import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.models.repositories.UsuarioRepository;
import com.churninsight.one.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private com.churninsight.one.models.repositories.RolRepository rolRepository;

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public Page<Usuario> listar(Integer pagina, Integer tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<Usuario> resultado = this.usuarioRepository.findAll(pageable);
        if (resultado == null || resultado.isEmpty()) {
            throw new ResourceNotFoundException("usuarios");
        }
        return resultado;
    }

    @Transactional(readOnly = true)
    @Override
    public ApiResponse buscarPorId(String id) {
        Usuario resultado = this.usuarioRepository.findById(id).orElse(null);
        if (resultado == null) {
            throw new ResourceNotFoundException("usuario", "id", id);
        }
        ApiResponse response = new ApiResponse("Usuario obtenido con éxito", true, resultado);
        return response;
    }

    @Transactional
    @Override
    public ApiResponse crear(UsuarioDto usuarioDto) {
        try {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(usuarioDto.nombre());
            nuevoUsuario.setPApellido(usuarioDto.pApellido());
            nuevoUsuario.setSApellido(usuarioDto.sApellido());
            nuevoUsuario.setEmail(usuarioDto.email());
            nuevoUsuario.setPassword(passwordEncoder.encode(usuarioDto.password()));
            nuevoUsuario.setTelefono(usuarioDto.telefono());
            nuevoUsuario.setFechaNacimiento(usuarioDto.fechaNacimiento());
            nuevoUsuario.setGenero(usuarioDto.genero());
            nuevoUsuario.setTieneConyuge(usuarioDto.tieneConyuge());
            nuevoUsuario.setTieneDependientes(usuarioDto.tieneDependientes());

            // Asignar rol USUARIO por defecto
            Rol rolUsuario = rolRepository.findByNombre("USUARIO")
                    .orElseThrow(() -> new ResourceNotFoundException("Rol USUARIO no encontrado en la base de datos"));
            nuevoUsuario.setRoles(java.util.List.of(rolUsuario));

            Usuario resultado = this.usuarioRepository.save(nuevoUsuario);
            System.out.println("ID: " + nuevoUsuario.getId());
            ApiResponse response = new ApiResponse("Usuario creado con éxito", true, resultado);
            return response;
        } catch (DataAccessException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Transactional
    @Override
    public ApiResponse editar(UsuarioDto usuarioDto) {
        try {
            if (this.existeId(usuarioDto.id()) && usuarioDto.id().equals(usuarioDto.id())) {
                Usuario usuario = this.usuarioRepository.findById(usuarioDto.id()).orElse(null);
                if (usuario != null) {
                    usuario.setNombre(usuarioDto.nombre());
                    usuario.setPApellido(usuarioDto.pApellido());
                    usuario.setSApellido(usuarioDto.sApellido());
                    usuario.setEmail(usuarioDto.email());

                    // Solo actualizar la contraseña si se proporciona una nueva
                    if (usuarioDto.password() != null && !usuarioDto.password().trim().isEmpty()) {
                        usuario.setPassword(passwordEncoder.encode(usuarioDto.password()));
                    }

                    usuario.setTelefono(usuarioDto.telefono());
                    usuario.setFechaNacimiento(usuarioDto.fechaNacimiento());
                    usuario.setGenero(usuarioDto.genero());

                    Usuario resultado = this.usuarioRepository.save(usuario);
                    ApiResponse response = new ApiResponse("Usuario editado con éxito", true, resultado);
                    return response;
                } else {
                    throw new ResourceNotFoundException("usuario", "id", usuarioDto.id());
                }
            } else {
                throw new ResourceNotFoundException("usuario", "id", usuarioDto.id());
            }

        } catch (DataAccessException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Transactional
    @Override
    public ApiResponse borradoLogico(String id) {
        ApiResponse existeUsuario = this.buscarUsuarioActivoPorId(id);
        if (existeUsuario.getData() == null) {
            throw new ResourceNotFoundException("Usuario", "id", id);
        } else {
            this.usuarioRepository.softDeleteById(id);
            ApiResponse response = new ApiResponse("Usuario eliminado con éxito", true);
            return response;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean existeId(String id) {
        return this.usuarioRepository.existsById(id);
    }

    @Override
    public Page<Usuario> listarUsuariosActivos(Integer pagina, Integer tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<Usuario> resultado = this.usuarioRepository.findAllActiveUsuarios(pageable);
        if (resultado == null || resultado.isEmpty()) {
            throw new ResourceNotFoundException("usuarios");
        }
        return resultado;
    }

    @Override
    public Page<Usuario> listarUsuariosEliminados(Integer pagina, Integer tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<Usuario> resultado = this.usuarioRepository.findDeleted(pageable);
        if (resultado == null || resultado.isEmpty()) {
            throw new ResourceNotFoundException("usuarios");
        }
        return resultado;
    }

    @Override
    public ApiResponse buscarUsuarioActivoPorId(String id) {
        Usuario resultado = this.usuarioRepository.findActiveById(id).orElse(null);
        if (resultado == null) {
            throw new ResourceNotFoundException("usuario", "id", id);
        }
        ApiResponse response = new ApiResponse("Usuario obtenido con éxito", true, resultado);
        return response;
    }

    @Transactional
    @Override
    public ApiResponse asignarRol(String usuarioId, Long rolId) {
        Usuario usuario = this.usuarioRepository.findActiveById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("usuario", "id", usuarioId));

        Rol rol = rolRepository.findById(rolId)
                .orElseThrow(() -> new ResourceNotFoundException("rol", "id", rolId));

        if (!usuario.getRoles().contains(rol)) {
            usuario.getRoles().add(rol);
            usuarioRepository.save(usuario);
            return new ApiResponse("Rol asignado con éxito", true, null);
        } else {
            return new ApiResponse("El usuario ya tiene este rol", false, null);
        }
    }

    @Transactional
    @Override
    public ApiResponse removerRol(String usuarioId, Long rolId) {
        Usuario usuario = this.usuarioRepository.findActiveById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("usuario", "id", usuarioId));

        Rol rol = rolRepository.findById(rolId)
                .orElseThrow(() -> new ResourceNotFoundException("rol", "id", rolId));

        if (usuario.getRoles().contains(rol)) {
            usuario.getRoles().remove(rol);
            usuarioRepository.save(usuario);
            return new ApiResponse("Rol removido con éxito", true, null);
        } else {
            return new ApiResponse("El usuario no tiene este rol asignado", false, null);
        }
    }
}
