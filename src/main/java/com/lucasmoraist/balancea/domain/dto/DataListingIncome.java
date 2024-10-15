package com.lucasmoraist.balancea.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasmoraist.balancea.domain.entity.Income;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(
        name = "Data Listing Income",
        description = "Dados para listar renda",
        example = """
                {
                    "id": 1,
                    "description": "description",
                    "amount": 1000,
                    "date": "01/01/2021"
                }
                """
)
public record DataListingIncome(
        Long id,
        String description,
        BigDecimal amount,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date
) {
    public DataListingIncome(Income income) {
        this(income.getId(), income.getBudget().getDescription(), income.getBudget().getAmount(), income.getBudget().getDate());
    }
}
