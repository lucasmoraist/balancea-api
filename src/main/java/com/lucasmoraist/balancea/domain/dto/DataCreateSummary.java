package com.lucasmoraist.balancea.domain.dto;

import java.math.BigDecimal;
import java.util.List;

public record DataCreateSummary(
        BigDecimal totalIncome,
        BigDecimal totalExpense,
        BigDecimal totalBalance,
        List<DataExpenseByCategory> totalExpenseByCategory
) {
}
