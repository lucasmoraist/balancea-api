package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.dto.*;
import com.lucasmoraist.balancea.domain.model.Budget;
import com.lucasmoraist.balancea.domain.entity.Expense;
import com.lucasmoraist.balancea.exceptions.DuplicateBadgetException;
import com.lucasmoraist.balancea.exceptions.ResourceNotFoundException;
import com.lucasmoraist.balancea.repository.ExpenseRepository;
import com.lucasmoraist.balancea.service.CategoryService;
import com.lucasmoraist.balancea.service.ExpenseService;
import com.lucasmoraist.balancea.validations.ValidateMonthAndYear;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository repository;
    private final CategoryService categoryService;
    private final ValidateMonthAndYear validateMonthAndYear;

    @Override
    public DataDetailsExpense save(DataCreateExpense data) {
        log.info("Iniciando o salvamento da despesa com os dados: {}", data);
        this.validateDuplicateBadge(data);

        var budget = new Budget(data);
        log.info("Orçamento criado com sucesso: {}", budget);

        var category = this.categoryService.save(data);
        log.info("Categoria salva com sucesso: {}", category);

        var expense = Expense.builder()
                .budget(budget)
                .category(category)
                .build();

        this.repository.save(expense);
        log.info("Despesa salva com sucesso: {}", expense);

        return new DataDetailsExpense(expense);
    }

    @Override
    public Page<DataListingExpense> listAll(Pageable pageable) {
        log.info("Listando todas as despesas com paginação: {}", pageable);
        return this.repository.findAll(pageable)
                .map(DataListingExpense::new);
    }

    @Override
    public List<DataListingExpense> listByDescription(String term) {
        log.info("Buscando despesas com a descrição: {}", term);
        return this.repository.listExpenseByTerm(term)
                .stream()
                .map(DataListingExpense::new)
                .toList();
    }

    @Override
    public List<DataDetailsExpense> listByMonthAndYear(int month, int year) {
        log.info("Listando despesas para o mês: {} e ano: {}", month, year);
        this.validateMonthAndYear.validate(month, year);

        var expenses = this.repository.findAll();
        return expenses.stream()
                .filter(i -> i.getBudget().getDate().getMonthValue() == month && i.getBudget().getDate().getYear() == year)
                .map(DataDetailsExpense::new)
                .toList();
    }

    @Override
    public DataDetailsExpense findById(Long id) {
        log.info("Buscando despesa com ID: {}", id);
        var expense = this.getExpense(id);
        return new DataDetailsExpense(expense);
    }

    @Override
    public DataDetailsExpense update(Long id, DataCreateExpense data) {
        log.info("Atualizando despesa com ID: {}", id);
        var expense = this.getExpense(id);

        expense.updateData(data);
        this.repository.save(expense);
        log.info("Despesa atualizada com sucesso: {}", expense);

        return new DataDetailsExpense(expense);
    }

    @Override
    public void delete(Long id) {
        log.info("Removendo despesa com ID: {}", id);
        var expense = this.getExpense(id);
        this.repository.delete(expense);
        log.info("Despesa removida com sucesso: {}", expense);
    }

    private Expense getExpense(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Despesa não encontrada com ID: {}", id);
                    return new ResourceNotFoundException("Expense not found");
                });
    }

    private void validateDuplicateBadge(DataCreateExpense data) {
        var exists = this.repository.existsByDescriptionAndMonth(data.description(), data.date().getMonthValue());

        if (exists) {
            log.error("A despesa já existe para o mês: {} com a descrição: {}", data.date().getMonthValue(), data.description());
            throw new DuplicateBadgetException("Expense already exists for this month");
        }
        log.info("Validação de despesa concluída sem duplicatas para o mês: {}", data.date().getMonthValue());
    }


}
