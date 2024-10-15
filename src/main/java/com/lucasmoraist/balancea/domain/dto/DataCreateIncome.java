package com.lucasmoraist.balancea.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(
        name = "Data Create Income",
        description = "Dados para criação de uma receita",
        example = """
                {
                    "description": "Salário",
                    "amount": 1000.0,
                    "date": "01/01/2021"
                }
                """
)
public record DataCreateIncome(

        @NotBlank(message = "Description is required")
        String description,
        @NotNull(message = "Amount is required") @Positive(message = "Amount must be positive")
        BigDecimal amount,
        @NotNull(message = "Date is required") @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date
) {
}
