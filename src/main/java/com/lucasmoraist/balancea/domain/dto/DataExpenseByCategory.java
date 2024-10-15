package com.lucasmoraist.balancea.domain.dto;

import com.lucasmoraist.balancea.domain.enums.Categories;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(
        name = "Data Expense By Category",
        description = "Despesas por categoria",
        example = """
                {
                    "category": "category",
                    "total": "1000"
                }
                """
)
public record DataExpenseByCategory(
        String category,
        String total
) {
    public DataExpenseByCategory(Categories key, BigDecimal bigDecimal) {
        this(key.name(), bigDecimal.toString());
    }
}
