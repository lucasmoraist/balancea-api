package com.lucasmoraist.balancea.domain.entity;

import com.lucasmoraist.balancea.domain.dto.DataCreateBudgets;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Expense")
@Table(name = "t_expense")
@EqualsAndHashCode(of = "id")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Budget budget;

    public Expense(DataCreateBudgets data) {
        this.budget = new Budget(data);
    }

    public void updateData(DataCreateBudgets data) {
        this.budget.updateData(data);
    }
}
