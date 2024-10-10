package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.dto.DataCreateSummary;
import com.lucasmoraist.balancea.domain.dto.DataExpenseByCategory;
import com.lucasmoraist.balancea.repository.ExpenseRepository;
import com.lucasmoraist.balancea.repository.IncomeRepository;
import com.lucasmoraist.balancea.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    @Override
    public DataCreateSummary createSummary(int month, int year) {
        var incomes = this.incomeRepository.findAll();
        var expenses = this.expenseRepository.findAll();

        var totalIncome = incomes.stream()
                .filter(i -> i.getBudget().getDate().getMonthValue() == month && i.getBudget().getDate().getYear() == year)
                .map(i -> i.getBudget().getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var totalExpense = expenses.stream()
                .filter(e -> e.getBudget().getDate().getMonthValue() == month && e.getBudget().getDate().getYear() == year)
                .map(e -> e.getBudget().getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var totalBalance = totalIncome.subtract(totalExpense);

        var totalExpenseByCategory = expenses.stream()
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

        return new DataCreateSummary(
                totalIncome,
                totalExpense,
                totalBalance,
                totalExpenseByCategory
        );
    }
}
