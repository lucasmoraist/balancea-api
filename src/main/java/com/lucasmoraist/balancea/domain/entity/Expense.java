package com.lucasmoraist.balancea.domain.entity;

import com.lucasmoraist.balancea.domain.enums.Categories;
import com.lucasmoraist.balancea.domain.dto.DataCreateExpense;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Expense(DataCreateExpense data) {
        this.budget = new Budget(data);
        if(data.category() == null){
            this.category = new Category(null, Categories.OUTROS);
        } else {
          this.category = new Category(null, data.category());
        }
    }

    public void updateData(DataCreateExpense data) {
        this.budget.updateData(data);
    }
}
