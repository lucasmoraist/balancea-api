package com.lucasmoraist.balancea.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(
        name = "Data Create User",
        description = "Dados para criação de um usuário",
        example = """
                {
                    "name": "Lucas",
                    "email": "lucas@lucas.com",
                    "password": "123456
                }
                """
)
public record DataCreateUser(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required") @Email(message = "Email is invalid")
        String email,

        @NotBlank(message = "Password is required")
        String password
) {
}
