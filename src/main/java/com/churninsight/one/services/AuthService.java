package com.churninsight.one.services;

import com.churninsight.one.models.dto.request.AuthResponse;
import com.churninsight.one.models.entities.usuario.UsuarioDto;
import com.churninsight.one.models.peyload.LoginRequest;

public interface AuthService {

    public AuthResponse loginUsuario(LoginRequest loginRequest);

    public AuthResponse crearUsuario(UsuarioDto usuarioDto);

}
