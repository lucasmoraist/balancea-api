package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.dto.*;
import com.lucasmoraist.balancea.domain.entity.Budget;
import com.lucasmoraist.balancea.domain.entity.Expense;
import com.lucasmoraist.balancea.exceptions.DuplicateBadgetException;
import com.lucasmoraist.balancea.exceptions.ResourceNotFoundException;
import com.lucasmoraist.balancea.repository.ExpenseRepository;
import com.lucasmoraist.balancea.service.CategoryService;
import com.lucasmoraist.balancea.service.ExpenseService;
import com.lucasmoraist.balancea.validations.ValidateMonthAndYear;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository repository;
    private final CategoryService categoryService;
    private final ValidateMonthAndYear validateMonthAndYear;

    @Override
    public DataDetailsExpense save(DataCreateExpense data) {
        this.validateDuplicateBadge(data);

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
    public List<DataListingExpense> listByDescription(String term) {
        return this.repository.listExpenseByTerm(term)
                .stream()
                .map(DataListingExpense::new)
                .toList();
    }

    @Override
    public List<DataDetailsExpense> listByMonthAndYear(int month, int year) {
        var expenses = this.repository.findAll();
        this.validateMonthAndYear.validate(month, year);
        return expenses.stream()
                .filter(i -> i.getBudget().getDate().getMonthValue() == month && i.getBudget().getDate().getYear() == year)
                .map(DataDetailsExpense::new)
                .toList()
                ;
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
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));
    }

    private void validateDuplicateBadge(DataCreateExpense data) {
        var exists = this.repository.existsByDescriptionAndMonth(data.description(), data.date().getMonthValue());

        if (exists) {
            throw new DuplicateBadgetException("Expense already exists for this month");
        }
    }

}
