package com.lucasmoraist.balancea.domain.dto;

import com.lucasmoraist.balancea.domain.entity.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DataListingExpense(
        Long id,
        String description,
        BigDecimal amount,
        LocalDate date
) {
    public DataListingExpense(Expense expense) {
        this(expense.getId(), expense.getBudget().getDescription(), expense.getBudget().getAmount(), expense.getBudget().getDate());
    }
}
