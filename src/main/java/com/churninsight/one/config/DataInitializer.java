package com.churninsight.one.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.churninsight.one.models.entities.rol.Rol;
import com.churninsight.one.models.entities.usuario.Usuario;
import com.churninsight.one.models.repositories.RolRepository;
import com.churninsight.one.models.repositories.UsuarioRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(RolRepository rolRepository, UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            // Get Roles from database
            Rol adminRol = rolRepository.findByNombre("ADMIN").orElse(null);
            Rol userRol = rolRepository.findByNombre("USUARIO").orElse(null);

            // Create Admin User if not exist
            if (usuarioRepository.findByEmail("admin@churninsight.com").isEmpty() && adminRol != null) {
                Usuario admin = new Usuario();
                admin.setNombre("Admin");
                admin.setPApellido("System");
                admin.setSApellido("User");
                admin.setEmail("admin@churninsight.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setTelefono("0000000000");
                admin.setFechaNacimiento(LocalDate.now());
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
