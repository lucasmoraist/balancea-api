package com.lucasmoraist.balancea.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(
        name = "Data Login User",
        description = "Informações para login",
        example = """
                {
                    "email": "lucas@lucas.com"
                    "password": "password"
                }
                """
)
public record DataLoginUser(

        @NotBlank(message = "Email is required") @Email(message = "Email is invalid")
        String email,

        @NotBlank(message = "Password is required")
        String password
) {
}
