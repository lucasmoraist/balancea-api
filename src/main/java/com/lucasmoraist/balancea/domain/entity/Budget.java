package com.lucasmoraist.balancea.domain.entity;

import com.lucasmoraist.balancea.domain.dto.DataCreateBudgets;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Budget {

    private String description;
    private BigDecimal amount;
    private LocalDate date;

    public Budget(DataCreateBudgets data) {
        this.description = data.description();
        this.amount = data.amount();
        this.date = data.date();
    }

    public void updateData(DataCreateBudgets data) {
        if (data.description() != null) {
            this.description = data.description();
        }
        if (data.amount() != null) {
            this.amount = data.amount();
        }
        if (data.date() != null) {
            this.date = data.date();
        }
    }
}