package com.lucasmoraist.balancea.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Token DTO",
        description = "DTO para token",
        example = """
                {
                    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJCYWxhbmNlYSBBUEkiLCJzdWIiOiJsdWNhc0BsdWNhcy5jb20ifQ.f8I2yHWHXbC5-02G9jH9GTz1rGHZ7SlOG_bC2ZpHcV4"
                }
                """
)
public record TokenDTO(String token) {
}
