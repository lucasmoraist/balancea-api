package com.lucasmoraist.balancea.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasmoraist.balancea.domain.entity.Expense;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(
        name = "Data Details Expense",
        description = "Detalhes de uma despesa",
        example = """
                {
                    "description": "description",
                    "amount": 1000,
                    "date": "01/01/2021",
                    "category": "category"
                }
                """
)
public record DataDetailsExpense(
        String description,
        BigDecimal amount,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date,
        String category
) {
        public DataDetailsExpense(Expense expense) {
                this(
                        expense.getBudget().getDescription(),
                        expense.getBudget().getAmount(),
                        expense.getBudget().getDate(),
                        expense.getCategory().getName().toString()
                );
        }
}
