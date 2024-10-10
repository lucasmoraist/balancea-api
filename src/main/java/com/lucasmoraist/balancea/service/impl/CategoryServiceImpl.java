package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.dto.DataCreateExpense;
import com.lucasmoraist.balancea.domain.entity.Category;
import com.lucasmoraist.balancea.repository.CategoryRepository;
import com.lucasmoraist.balancea.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public Category save(DataCreateExpense data) {
        return this.repository.save(new Category(data));
    }
}
