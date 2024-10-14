package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.dto.DataCreateExpense;
import com.lucasmoraist.balancea.domain.entity.Category;
import com.lucasmoraist.balancea.domain.enums.Categories;
import com.lucasmoraist.balancea.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryServiceImpl service;

    @Test
    @DisplayName("Should save a category")
    void case01() {
        var data = new DataCreateExpense(
                "description",
                BigDecimal.valueOf(100),
                LocalDate.of(2024, 1, 14),
                Categories.ALIMENTACAO
        );

        var category = new Category(data);

        when(repository.save(category)).thenReturn(category);

        assertEquals(category, this.service.save(data));
    }

    @Test
    @DisplayName("Should return category Other when category is null")
    void case02() {
        var data = new DataCreateExpense(
                "description",
                BigDecimal.valueOf(100),
                LocalDate.of(2024, 1, 14),
                null
        );

        var category = new Category(data);

        assertEquals(Categories.OUTROS, category.getName());
    }

}