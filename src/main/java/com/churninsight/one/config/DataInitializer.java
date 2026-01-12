package com.churninsight.one.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.churninsight.one.models.entities.Rol;
import com.churninsight.one.models.entities.usuario.Usuario;
import com.churninsight.one.models.repositories.RolRepository;
import com.churninsight.one.models.repositories.UsuarioRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(RolRepository rolRepository, UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            // Create Roles if not exist
            Rol adminRol = rolRepository.findByNombre("ROLE_ADMIN").orElseGet(() -> {
                Rol rol = new Rol();
                rol.setNombre("ROLE_ADMIN");
                rol.setDescripcion("Administrador del sistema");
                rol.setCreatedAt(LocalDateTime.now());
                return rolRepository.save(rol);
            });

            Rol userRol = rolRepository.findByNombre("ROLE_USER").orElseGet(() -> {
                Rol rol = new Rol();
                rol.setNombre("ROLE_USER");
                rol.setDescripcion("Usuario estandar");
                rol.setCreatedAt(LocalDateTime.now());
                return rolRepository.save(rol);
            });

            // Create Admin User if not exist
            if (usuarioRepository.findByEmail("admin@churninsight.com").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNombre("Admin");
                admin.setPApellido("System");
                admin.setSApellido("User");
                admin.setEmail("admin@churninsight.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setTelefono("0000000000");
                admin.setFechaNacimiento(LocalDateTime.now());
                admin.setGenero("O");
                admin.setTieneConyuge(false);
                admin.setTieneDependientes(false);
                admin.setRoles(List.of(adminRol));

                usuarioRepository.save(admin);
                System.out.println("---------------------------------------------");
                System.out.println("Admin user created: admin@churninsight.com / admin123");
                System.out.println("---------------------------------------------");
            }
        };
    }
}
