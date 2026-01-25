package com.churninsight.one.config.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.churninsight.one.security.JwtAuthenticationFilter;
import com.churninsight.one.security.JwtUtils;
import com.churninsight.one.services.implementations.UsuarioServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private JwtUtils JwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/v3/api-docs.yaml",
                                "/webjars/**")
                        .permitAll()
                        // GET: Solo usuarios con rol USUARIO o ADMIN
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/**").hasAnyRole("USUARIO", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/roles/**").hasAnyRole("USUARIO", "ADMIN")
                        // Usuarios: POST, PUT, DELETE solo para ADMIN
                        .requestMatchers(HttpMethod.POST, "/api/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/usuarios/**").hasRole("ADMIN")
                        // Roles: POST, PUT, DELETE solo para ADMIN
                        .requestMatchers(HttpMethod.POST, "/api/roles/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/roles/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/roles/**").hasRole("ADMIN")
                        // Gestión de servicios
                        .requestMatchers(HttpMethod.GET, "/api/servicios/**").hasAnyRole("USUARIO", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/servicios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/servicios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/servicios/**").hasRole("ADMIN")
                        // Gestión de planes
                        .requestMatchers(HttpMethod.GET, "/api/planes/**").hasAnyRole("USUARIO", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/planes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/planes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/planes/**").hasRole("ADMIN")
                        // Gestión de ofertas
                        .requestMatchers(HttpMethod.GET, "/api/ofertas/**").hasAnyRole("USUARIO", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/ofertas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/ofertas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/ofertas/**").hasRole("ADMIN")
                        // Gestión de permisos: Solo ADMIN
                        .requestMatchers("/permisos/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(new JwtAuthenticationFilter(this.JwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider AuthenticationProvider(UsuarioServiceImpl usuarioServiceImpl) {
        // provider que manejara el logeo
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(usuarioServiceImpl);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
