package com.lucasmoraist.balancea.repository;

import com.lucasmoraist.balancea.domain.entity.Expense;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("""
            SELECT e FROM Expense e
            WHERE LOWER(e.budget.description)
            LIKE LOWER(CONCAT('%', :term, '%'))
            """)
    List<Expense> listExpenseByTerm(@PathVariable String term);

    @Query("""
            SELECT CASE WHEN COUNT(i) > 0
            THEN TRUE ELSE FALSE END
            FROM Expense e
            WHERE e.budget.description = :description
            AND MONTH(e.budget.date) = :month
            """)
    boolean existsByDescriptionAndMonth(String description, int month);
}
