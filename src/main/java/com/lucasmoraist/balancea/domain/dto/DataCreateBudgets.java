package com.lucasmoraist.balancea.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DataCreateBudgets (

        @NotBlank
        String description,
        @NotNull @Positive
        BigDecimal amount,

        @NotNull @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date
) {
}
