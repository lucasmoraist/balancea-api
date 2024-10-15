package com.lucasmoraist.balancea.domain.dto;

import com.lucasmoraist.balancea.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Data Details User",
        description = "Detalhes de um usuário",
        example = """
                {
                    "name": "name",
                    "email": "email"
                }
                """
)
public record DataDetailsUser(
        String name,
        String email
) {
    public DataDetailsUser(User user) {
        this(user.getName(), user.getEmail());
    }
}
