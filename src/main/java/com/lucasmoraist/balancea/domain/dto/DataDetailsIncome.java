package com.lucasmoraist.balancea.domain.dto;

import com.lucasmoraist.balancea.domain.entity.Income;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DataDetailsIncome(
        String description,
        BigDecimal amount,
        LocalDate date
) {
    public DataDetailsIncome(Income income) {
        this(income.getBudget().getDescription(), income.getBudget().getAmount(), income.getBudget().getDate());
    }
}
