package com.churninsight.one.services;

import org.springframework.data.domain.Page;

import com.churninsight.one.models.entities.usuario.Usuario;
import com.churninsight.one.models.entities.usuario.UsuarioDto;
import com.churninsight.one.models.peyload.ApiResponse;

public interface UsuarioService {

    public Page<Usuario> listar(Integer pagina, Integer tamanio);

    public ApiResponse buscarPorId(String id);

    public ApiResponse crear(UsuarioDto usuarioDto);

    public ApiResponse editar(UsuarioDto usuarioDto);

    public Boolean existeId(String id);

    public Page<Usuario> listarUsuariosActivos(Integer pagina, Integer tamanio);

    public Page<Usuario> listarUsuariosEliminados(Integer pagina, Integer tamanio);

    public ApiResponse buscarUsuarioActivoPorId(String id);

    public ApiResponse borradoLogico(String id);

    public ApiResponse asignarRol(String usuarioId, Long rolId);

    public ApiResponse removerRol(String usuarioId, Long rolId);
}
