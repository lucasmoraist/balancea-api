package com.lucasmoraist.balancea.domain.entity;

import com.lucasmoraist.balancea.domain.enums.Categories;
import com.lucasmoraist.balancea.domain.dto.DataCreateExpense;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Expense")
@Table(name = "t_expense")
@EqualsAndHashCode(of = "id")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Budget budget;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public void updateData(DataCreateExpense data) {
        this.budget.updateData(data);
    }
}
