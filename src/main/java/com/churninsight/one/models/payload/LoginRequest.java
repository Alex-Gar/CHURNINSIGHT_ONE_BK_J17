package com.churninsight.one.models.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "El email es requerido") @Email(message = "El formato del email es inválido") String email,

        @NotBlank(message = "El password es requerido") String password) {
}
