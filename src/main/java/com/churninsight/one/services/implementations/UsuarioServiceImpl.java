package com.churninsight.one.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.churninsight.one.models.entities.usuario.Usuario;
import com.churninsight.one.models.repositories.UsuarioRepository;
import com.churninsight.one.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listar() {
        return this.usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(String id) {
        return this.usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario editar(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
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
