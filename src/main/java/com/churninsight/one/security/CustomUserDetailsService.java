package com.churninsight.one.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.churninsight.one.models.repositories.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)
                .map(usuario -> org.springframework.security.core.userdetails.User.builder()
                        .username(usuario.getEmail())
                        .password(usuario.getPassword())
                        .authorities(obtenerAutoridades(usuario))
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + username));
    }

    /**
     * Obtiene los roles y permisos del usuario
     * - Los roles se prefijan con "ROLE_" (ej: ROLE_ADMIN)
     * - Los permisos se agregan directamente (ej: CREATE, READ, UPDATE, DELETE)
     */
    private Collection<? extends GrantedAuthority> obtenerAutoridades(
            com.churninsight.one.models.entities.usuario.Usuario usuario) {
        return usuario.getRoles().stream()
                .flatMap(rol -> {
                    // Agregar el rol con prefijo ROLE_
                    var rolAuthority = new SimpleGrantedAuthority("ROLE_" + rol.getNombre());

                    // Agregar todos los permisos del rol
                    var permisosAuthorities = rol.getPermisos().stream()
                            .map(permiso -> new SimpleGrantedAuthority(permiso.getNombre()))
                            .collect(Collectors.toList());

                    permisosAuthorities.add(rolAuthority);
                    return permisosAuthorities.stream();
                })
                .collect(Collectors.toSet());
    }

   

}
