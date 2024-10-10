package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.dto.DataCreateSummary;
import com.lucasmoraist.balancea.domain.dto.DataExpenseByCategory;
import com.lucasmoraist.balancea.repository.ExpenseRepository;
import com.lucasmoraist.balancea.repository.IncomeRepository;
import com.lucasmoraist.balancea.service.SummaryService;
import com.lucasmoraist.balancea.validations.ValidateMonthAndYear;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final ValidateMonthAndYear validateMonthAndYear;

    @Override
    public DataCreateSummary createSummary(int month, int year) {
        this.validateMonthAndYear.validate(month, year);

        var totalIncome = this.sumIncomes(month, year);
        var totalExpense = this.sumExpenses(month, year);

        var totalBalance = totalIncome.subtract(totalExpense);

        var totalExpenseByCategory = this.sumExpensesByCategory(month, year);

        return new DataCreateSummary(
                totalIncome,
                totalExpense,
                totalBalance,
                totalExpenseByCategory
        );
    }

    private BigDecimal sumIncomes(int month, int year){
        var incomes = this.incomeRepository.findAll();

        return incomes.stream()
                .filter(i -> i.getBudget().getDate().getMonthValue() == month && i.getBudget().getDate().getYear() == year)
                .map(i -> i.getBudget().getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal sumExpenses(int month, int year) {
        var expenses = this.expenseRepository.findAll();

        return expenses.stream()
                .filter(e -> e.getBudget().getDate().getMonthValue() == month && e.getBudget().getDate().getYear() == year)
                .map(e -> e.getBudget().getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<DataExpenseByCategory> sumExpensesByCategory(int month, int year) {
        var expenses = this.expenseRepository.findAll();

        return expenses.stream()
                .filter(e -> e.getBudget().getDate().getMonthValue() == month && e.getBudget().getDate().getYear() == year)
                .collect(
                        // Agrupando as despesas pelo nome da categoria
                        Collectors.groupingBy(
                                e -> e.getCategory().getName(),
                                // Para cada grupo de despesas, soma os valores de Budget
                                // Começando em ZERO
                                // Pegando cada valor de Budget
                                // E no final somando todos os valores
                                Collectors.reducing(BigDecimal.ZERO, e -> e.getBudget().getAmount(), BigDecimal::add)
                        )
                )
                // Transformando o agrupamento em uma stream de chave e valor
                .entrySet()
                .stream()
                // Mapeando que para cada categoria, cria um objeto DataExpenseByCategory
                .map(e -> new DataExpenseByCategory(e.getKey(), e.getValue()))
                .toList();
    }

}
