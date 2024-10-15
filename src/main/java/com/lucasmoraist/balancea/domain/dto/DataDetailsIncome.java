package com.lucasmoraist.balancea.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasmoraist.balancea.domain.entity.Income;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(
        name = "Data Details Income",
        description = "Detalhes de uma receita",
        example = """
                {
                    "description": "description",
                    "amount": 1000,
                    "date": "01/01/2021"
                }
                """
)
public record DataDetailsIncome(
        String description,
        BigDecimal amount,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date
) {
    public DataDetailsIncome(Income income) {
        this(income.getBudget().getDescription(), income.getBudget().getAmount(), income.getBudget().getDate());
    }
}
