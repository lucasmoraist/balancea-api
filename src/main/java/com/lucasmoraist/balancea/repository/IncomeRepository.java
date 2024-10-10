package com.lucasmoraist.balancea.repository;

import com.lucasmoraist.balancea.domain.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query("""
            SELECT CASE WHEN COUNT(i) > 0
            THEN TRUE ELSE FALSE END
            FROM Income i
            WHERE i.budget.description = :description
            AND MONTH(i.budget.date) = :month
            """)
    boolean existsByDescriptionAndMonth(String description, int month);

    @Query("""
            SELECT i FROM Income i
            WHERE LOWER(i.budget.description)
            LIKE LOWER(CONCAT('%', :term, '%'))
            """)
    List<Income> listIncomeByTerm(@PathVariable String term);
}
