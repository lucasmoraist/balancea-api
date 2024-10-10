package com.lucasmoraist.balancea.service;

import com.lucasmoraist.balancea.domain.dto.DataCreateExpense;
import com.lucasmoraist.balancea.domain.entity.Category;

public interface CategoryService {
    Category save(DataCreateExpense data);
}
