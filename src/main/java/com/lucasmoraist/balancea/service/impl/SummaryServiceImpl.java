package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.dto.DataCreateSummary;
import com.lucasmoraist.balancea.domain.dto.DataExpenseByCategory;
import com.lucasmoraist.balancea.repository.ExpenseRepository;
import com.lucasmoraist.balancea.repository.IncomeRepository;
import com.lucasmoraist.balancea.service.SummaryService;
import com.lucasmoraist.balancea.validations.ValidateMonthAndYear;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SummaryServiceImpl implements SummaryService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final ValidateMonthAndYear validateMonthAndYear;

    @Override
    public DataCreateSummary createSummary(int month, int year) {
        log.info("Iniciando a criação do resumo para o mês: {} e ano: {}", month, year);
        this.validateMonthAndYear.validate(month, year);

        var totalIncome = this.sumIncomes(month, year);
        log.info("Total de receitas para o mês {}: {}", month, totalIncome);

        var totalExpense = this.sumExpenses(month, year);
        log.info("Total de despesas para o mês {}: {}", month, totalExpense);

        var totalBalance = totalIncome.subtract(totalExpense);
        log.info("Saldo total para o mês {}: {}", month, totalBalance);

        var totalExpenseByCategory = this.sumExpensesByCategory(month, year);
        log.info("Despesas por categoria para o mês {}: {}", month, totalExpenseByCategory);

        return new DataCreateSummary(
                totalIncome,
                totalExpense,
                totalBalance,
                totalExpenseByCategory
        );
    }

    private BigDecimal sumIncomes(int month, int year) {
        log.info("Calculando a soma das receitas para o mês: {} e ano: {}", month, year);
        var incomes = this.incomeRepository.findAll();

        var totalIncomes = incomes.stream()
                .filter(i -> i.getBudget().getDate().getMonthValue() == month && i.getBudget().getDate().getYear() == year)
                .map(i -> i.getBudget().getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        log.info("Soma total de receitas: {}", totalIncomes);
        return totalIncomes;
    }

    private BigDecimal sumExpenses(int month, int year) {
        log.info("Calculando a soma das despesas para o mês: {} e ano: {}", month, year);
        var expenses = this.expenseRepository.findAll();

        var totalExpenses = expenses.stream()
                .filter(e -> e.getBudget().getDate().getMonthValue() == month && e.getBudget().getDate().getYear() == year)
                .map(e -> e.getBudget().getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        log.info("Soma total de despesas: {}", totalExpenses);
        return totalExpenses;
    }

    private List<DataExpenseByCategory> sumExpensesByCategory(int month, int year) {
        log.info("Calculando despesas por categoria para o mês: {} e ano: {}", month, year);
        var expenses = this.expenseRepository.findAll();

        var expensesByCategory = expenses.stream()
                .filter(e -> e.getBudget().getDate().getMonthValue() == month && e.getBudget().getDate().getYear() == year)
                .collect(
                        Collectors.groupingBy(
                                e -> e.getCategory().getName(),
                                Collectors.reducing(BigDecimal.ZERO, e -> e.getBudget().getAmount(), BigDecimal::add)
                        )
                )
                .entrySet()
                .stream()
                .map(e -> new DataExpenseByCategory(e.getKey(), e.getValue()))
                .toList();

        log.info("Despesas agrupadas por categoria: {}", expensesByCategory);
        return expensesByCategory;
    }


}
