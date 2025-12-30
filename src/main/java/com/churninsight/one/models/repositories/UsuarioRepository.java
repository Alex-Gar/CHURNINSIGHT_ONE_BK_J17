package com.churninsight.one.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.churninsight.one.models.entities.usuario.Usuario;

@Repository
public interface  UsuarioRepository extends JpaRepository<Usuario, String> {

}
