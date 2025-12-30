package com.churninsight.one.services;

import java.util.List;

import com.churninsight.one.models.entities.usuario.Usuario;

public interface UsuarioService {

    public List<Usuario> listar();

    public Usuario buscarPorId(String id);

    public Usuario editar(Usuario usuario);

    public void eliminar(String id);

    public Boolean existeId(String id);

}
