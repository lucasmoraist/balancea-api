package com.lucasmoraist.balancea.domain.dto;

import com.lucasmoraist.balancea.domain.entity.Expense;
import com.lucasmoraist.balancea.domain.entity.Income;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DataListing(
        Long id,
        String description,
        BigDecimal amount,
        LocalDate date
) {
    public DataListing(Income income) {
        this(income.getId(), income.getBudget().getDescription(), income.getBudget().getAmount(), income.getBudget().getDate());
    }
    public DataListing(Expense expense) {
        this(expense.getId(), expense.getBudget().getDescription(), expense.getBudget().getAmount(), expense.getBudget().getDate());
    }
}
