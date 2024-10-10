package com.lucasmoraist.balancea.domain.entity;

import com.lucasmoraist.balancea.domain.dto.DataCreateExpense;
import com.lucasmoraist.balancea.domain.enums.Categories;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Category")
@Table(name = "t_category")
@EqualsAndHashCode(of = "id")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Categories name;

    public Category(DataCreateExpense data) {
        this.name = data.category() == null ? Categories.OUTROS : data.category();
    }
}
