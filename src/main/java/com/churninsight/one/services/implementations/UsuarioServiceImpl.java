package com.churninsight.one.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.churninsight.one.exceptions.BadRequestException;
import com.churninsight.one.exceptions.ResourceNotFoundException;
import com.churninsight.one.models.entities.usuario.Usuario;
import com.churninsight.one.models.repositories.UsuarioRepository;
import com.churninsight.one.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listar() {
        List<Usuario> resultado = this.usuarioRepository.findAll();
        if (resultado == null || resultado.isEmpty()) {
            throw new ResourceNotFoundException("usuarios");
        }
        return resultado;
    }

    @Override
    public Usuario buscarPorId(String id) {
        Usuario resultado = this.usuarioRepository.findById(id).orElse(null);
        if (resultado == null) {
            throw new ResourceNotFoundException("usuario", "id", id);
        }
        return resultado;
    }

    @Override
    public Usuario editar(Usuario usuario) {
        try {
            if (this.existeId(usuario.getId()) && usuario.getId().equals(usuario.getId())) {
                return this.usuarioRepository.save(usuario);

            } else {
                throw new ResourceNotFoundException("usuario", "id", usuario.getId());
            }
        } catch (DataAccessException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Override
    public void eliminar(String id) {
        Usuario existeUsuario = this.buscarPorId(id);
        if (existeUsuario == null) {
            throw new ResourceNotFoundException("Usuario", "id", id);
        } else {
            this.usuarioRepository.deleteById(id);
        }
    }

    @Override
    public Boolean existeId(String id) {
        return this.usuarioRepository.existsById(id);
    }

}
