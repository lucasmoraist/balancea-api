package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.entity.Budget;
import com.lucasmoraist.balancea.domain.entity.Expense;
import com.lucasmoraist.balancea.domain.entity.Income;
import com.lucasmoraist.balancea.repository.ExpenseRepository;
import com.lucasmoraist.balancea.repository.IncomeRepository;
import com.lucasmoraist.balancea.validations.ValidateMonthAndYear;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class SummaryServiceImplTest {

    @InjectMocks
    private SummaryServiceImpl summaryService;

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Mock
    private ValidateMonthAndYear validateMonthAndYear;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a summary with the correct values")
    void case01() {

        var income1 = Income.builder()
                .budget(Budget.builder()
                        .description("Salary")
                        .amount(BigDecimal.valueOf(1000))
                        .date(LocalDate.of(2024, 9, 10))
                        .build())
                .build();
        var income2 = Income.builder()
                .budget(Budget.builder()
                        .description("Freelance")
                        .amount(BigDecimal.valueOf(500))
                        .date(LocalDate.of(2024, 9, 15))
                        .build())
                .build();

        this.incomeRepository.save(income1);
        this.incomeRepository.save(income2);

        var expense1 = Expense.builder()
                .budget(Budget.builder()
                        .description("Lunch")
                        .amount(BigDecimal.valueOf(200))
                        .date(LocalDate.of(2024, 9, 12))
                        .build())
                .build();
        var expense2 = Expense.builder()
                .budget(Budget.builder()
                        .description("Bus")
                        .amount(BigDecimal.valueOf(100))
                        .date(LocalDate.of(2024, 9, 25))
                        .build())
                .build();

        this.expenseRepository.save(expense1);
        this.expenseRepository.save(expense2);

        int month = 9;
        int year = 2024;

        var result = summaryService.createSummary(month, year);

        System.out.println(result);
        assertThat(result.totalIncome()).isEqualByComparingTo("1500");
        assertThat(result.totalExpense()).isEqualByComparingTo("300");
        assertThat(result.totalBalance()).isEqualByComparingTo("1200");

        assertThat(result.totalExpenseByCategory())
                .hasSize(2)
                .extracting("category", "amount")
                .containsExactlyInAnyOrder(
                        tuple("Alimentação", BigDecimal.valueOf(200)),
                        tuple("Transporte", BigDecimal.valueOf(100))
                );

        verify(validateMonthAndYear, times(1)).validate(month, year);
    }

}