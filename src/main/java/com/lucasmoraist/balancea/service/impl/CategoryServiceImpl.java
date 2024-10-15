package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.dto.DataCreateExpense;
import com.lucasmoraist.balancea.domain.entity.Category;
import com.lucasmoraist.balancea.repository.CategoryRepository;
import com.lucasmoraist.balancea.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public Category save(DataCreateExpense data) {
        log.info("Iniciando o salvamento da categoria com os dados: {}", data);

        Category category = this.repository.save(new Category(data));

        log.info("Categoria salva com sucesso: {}", category);
        return category;
    }
}
