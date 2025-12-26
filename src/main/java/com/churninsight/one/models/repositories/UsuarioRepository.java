package com.churninsight.one.models.repositories;

import com.churninsight.one.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UsuarioRepository extends JpaRepository<Usuario, Long> {

}
