package com.lucasmoraist.balancea.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasmoraist.balancea.domain.entity.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DataDetailsExpense(
        String description,
        BigDecimal amount,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date
) {
        public DataDetailsExpense(Expense expense) {
                this(expense.getBudget().getDescription(), expense.getBudget().getAmount(), expense.getBudget().getDate());
        }
}
