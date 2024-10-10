package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.dto.DataCreateBudgets;
import com.lucasmoraist.balancea.domain.dto.DataDetailsBudget;
import com.lucasmoraist.balancea.domain.dto.DataListing;
import com.lucasmoraist.balancea.domain.entity.Expense;
import com.lucasmoraist.balancea.repository.ExpenseRepository;
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

    @Override
    public DataDetailsBudget save(DataCreateBudgets data) {
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

        var expense = new Expense(data);

        this.repository.save(expense);

        return new DataDetailsBudget(expense);
    }

    @Override
    public Page<DataListing> listAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(DataListing::new);
    }

    @Override
    public DataDetailsBudget findById(Long id) {
        var expense = this.getExpense(id);
        return new DataDetailsBudget(expense);
    }

    @Override
    public DataDetailsBudget update(Long id, DataCreateBudgets data) {
        var expense = this.getExpense(id);
        expense.updateData(data);
        this.repository.save(expense);
        return new DataDetailsBudget(expense);
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
