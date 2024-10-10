package com.lucasmoraist.balancea.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasmoraist.balancea.domain.entity.Expense;
import com.lucasmoraist.balancea.domain.entity.Income;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DataDetailsBudget(
        String description,
        BigDecimal amount,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date
) {
    public DataDetailsBudget(Income income) {
        this(income.getBudget().getDescription(), income.getBudget().getAmount(), income.getBudget().getDate());
    }

    public DataDetailsBudget(Expense expense) {
        this(expense.getBudget().getDescription(), expense.getBudget().getAmount(), expense.getBudget().getDate());
    }
}
