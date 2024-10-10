package com.lucasmoraist.balancea.domain.dto;

import com.lucasmoraist.balancea.domain.enums.Categories;

import java.math.BigDecimal;

public record DataExpenseByCategory(
        String category,
        String total
) {
    public DataExpenseByCategory(Categories key, BigDecimal bigDecimal) {
        this(key.name(), bigDecimal.toString());
    }
}
