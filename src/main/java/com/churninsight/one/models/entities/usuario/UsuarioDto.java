package com.churninsight.one.models.entities.usuario;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioDto(

        String id,

        @NotBlank(message = "El nombre es obligatorio") 
        @Size(max = 50, message = "El nombre no debe exceder 50 caracteres") 
        String nombre,
        @NotBlank(message = "El primer apellido es obligatorio") 
        @Size(max = 50, message = "El primer apellido no debe exceder 50 caracteres") 
        String pApellido,
        @Size(max = 50, message = "El segundo apellido no debe exceder 50 caracteres") 
        String sApellido,
        @NotBlank(message = "El email es obligatorio") @Email(message = "Formato de email inválido") @Size(max = 100, message = "El email no debe exceder 100 caracteres") 
        String email,
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres") 
        String password,
        @NotBlank(message = "El teléfono es obligatorio") @Pattern(regexp = "^[0-9]{10}$", message = "El teléfono debe contener 10 dígitos") 
        String telefono,
        @NotNull(message = "La fecha de nacimiento es obligatoria") 
        LocalDateTime fechaNacimiento,
        @NotBlank(message = "El género es obligatorio") @Pattern(regexp = "MASCULINO|FEMENINO|OTRO", message = "El género debe ser MASCULINO, FEMENINO u OTRO") 
        String genero,
        Boolean tieneConyuge,
        Boolean tieneDependientes,
        Boolean isEnabled,
        Boolean accountNoExpired,
        Boolean accountNoLocked,
        Boolean credentialNoExpired,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt

) {
}
