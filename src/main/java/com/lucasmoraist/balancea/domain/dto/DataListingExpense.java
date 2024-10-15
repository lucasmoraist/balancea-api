package com.lucasmoraist.balancea.domain.dto;

import com.lucasmoraist.balancea.domain.entity.Expense;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(
        name = "Data Listing Expense",
        description = "Dados para listar despesa",
        example = """
                {
                    "id": 1,
                    "description": "description",
                    "amount": 1000,
                    "date": "2021-01-01",
                    "category": "FOOD"
                }
                """
)
public record DataListingExpense(
        Long id,
        String description,
        BigDecimal amount,
        LocalDate date,
        String category
) {
    public DataListingExpense(Expense expense) {
        this(
                expense.getId(),
                expense.getBudget().getDescription(),
                expense.getBudget().getAmount(),
                expense.getBudget().getDate(),
                expense.getCategory().getName().toString()
        );
    }
}
