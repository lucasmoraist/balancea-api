package com.lucasmoraist.balancea.domain.entity;

import com.lucasmoraist.balancea.domain.dto.DataCreateIncome;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Income")
@Table(name = "t_income")
@EqualsAndHashCode(of = "id")
public class Income {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Budget budget;

    public Income(DataCreateIncome data) {
        this.budget = new Budget(data);
    }

    public void updateData(DataCreateIncome data) {
        this.budget.updateData(data);
    }
}
