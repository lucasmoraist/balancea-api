package com.lucasmoraist.balancea.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DataCreateIncome(

        @NotBlank(message = "Description is required")
        String description,
        @NotNull(message = "Amount is required") @Positive(message = "Amount must be positive")
        BigDecimal amount,
        @NotNull(message = "Date is required") @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date
) {
}
