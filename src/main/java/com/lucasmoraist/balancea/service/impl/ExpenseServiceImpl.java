package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.dto.*;
import com.lucasmoraist.balancea.domain.entity.Budget;
import com.lucasmoraist.balancea.domain.entity.Category;
import com.lucasmoraist.balancea.domain.entity.Expense;
import com.lucasmoraist.balancea.repository.CategoryRepository;
import com.lucasmoraist.balancea.repository.ExpenseRepository;
import com.lucasmoraist.balancea.service.CategoryService;
import com.lucasmoraist.balancea.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository repository;
    @Autowired
    private CategoryService categoryService;

    @Override
    public DataDetailsExpense save(DataCreateExpense data) {
        var expenses = this.repository.findAll();
        var month = LocalDate.now().getMonth();

        for (var expense : expenses) {
            if (expense.getBudget().getDate().getMonth().equals(month)) {
                if (expense.getBudget().getDescription().equals(data.description())) {
                    throw new RuntimeException("There is already an expense for this budget in this month");
                }
            }
            break;
        }

        var budget = new Budget(data);
        var category = this.categoryService.save(data);

        var expense = Expense.builder()
                .budget(budget)
                .category(category)
                .build();

        this.repository.save(expense);

        return new DataDetailsExpense(expense);
    }

    @Override
    public Page<DataListingExpense> listAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(DataListingExpense::new);
    }

    @Override
    public DataDetailsExpense findById(Long id) {
        var expense = this.getExpense(id);
        return new DataDetailsExpense(expense);
    }

    @Override
    public DataDetailsExpense update(Long id, DataCreateExpense data) {
        var expense = this.getExpense(id);
        expense.updateData(data);
        this.repository.save(expense);
        return new DataDetailsExpense(expense);
    }

    @Override
    public void delete(Long id) {
        var expense = this.getExpense(id);
        this.repository.delete(expense);
    }

    private Expense getExpense(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }
}
