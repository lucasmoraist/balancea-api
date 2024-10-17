package com.lucasmoraist.balancea.repository;

import com.lucasmoraist.balancea.domain.model.Budget;
import com.lucasmoraist.balancea.domain.entity.Expense;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository repository;

    @Test
    @DisplayName("Should return true if an expense with the given description and month exists")
    void case01() {

        var expense = Expense.builder()
                .budget(Budget.builder()
                        .description("Salary")
                        .date(LocalDate.of(2021, 1, 1))
                        .amount(BigDecimal.valueOf(1000))
                        .build())
                .build();

        this.repository.save(expense);

        boolean exists = this.repository.existsByDescriptionAndMonth("Salary", 1);
        assertTrue(exists);
    }

    @Test
    @DisplayName("Should return false if an expense with the given description and month does not exist")
    void case02() {
        boolean exists = this.repository.existsByDescriptionAndMonth("Salary", 2);
        assertFalse(exists);
    }

}