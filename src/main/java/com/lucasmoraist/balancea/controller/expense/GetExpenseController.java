package com.lucasmoraist.balancea.controller.expense;

import com.lucasmoraist.balancea.domain.dto.DataDetailsExpense;
import com.lucasmoraist.balancea.service.ExpenseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/expense")
@Tag(name = "Expense")
@Slf4j
public class GetExpenseController {

    @Autowired
    private ExpenseService service;

    @GetMapping("{id}")
    public ResponseEntity<DataDetailsExpense> get(@PathVariable Long id) {
        log.info("Buscando despesa com ID: {}", id);

        var expense = this.service.findById(id);

        log.info("Despesa encontrada: {}", expense);

        return ResponseEntity.ok().body(expense);
    }


}
