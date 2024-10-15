package com.lucasmoraist.balancea.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(
        name = "Data Create Summary",
        description = "Cria um resumo de dados financeiros.",
        example = """
                {
                    "totalIncome": 1000.0,
                    "totalExpense": 500.0,
                    "totalBalance": 500.0,
                    "totalExpenseByCategory": [
                        {
                            "category": "Food",
                            "total": 200.0
                        },
                        {
                            "category": "Transport",
                            "total": 300.0
                        }
                    ]
                }
                """
)
public record DataCreateSummary(
        BigDecimal totalIncome,
        BigDecimal totalExpense,
        BigDecimal totalBalance,
        List<DataExpenseByCategory> totalExpenseByCategory
) {
}
