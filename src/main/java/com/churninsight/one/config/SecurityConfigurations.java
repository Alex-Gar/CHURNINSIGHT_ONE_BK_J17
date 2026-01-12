package com.churninsight.one.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.churninsight.one.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
            JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**").permitAll()
                        // GET: Cualquier usuario autenticado (READ)
                        .requestMatchers(HttpMethod.GET, "/roles/**").authenticated()
                        // POST, PUT, DELETE: Solo ADMIN puede modificar
                        .requestMatchers(HttpMethod.POST, "/roles/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/roles/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/roles/**").hasRole("ADMIN")
                        // Validaciones específicas de usuarios
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/usuarios/**")
                        .hasRole("ADMIN")
                        .requestMatchers("/api/usuarios/*/roles/**").hasRole("ADMIN")
                        .requestMatchers("/api/usuarios/eliminados").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
