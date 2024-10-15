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

import java.util.List;

@RestController
@RequestMapping("/v2/expense")
@Tag(name = "Expense")
@Slf4j
public class ListExpenseByDate {

    @Autowired
    private ExpenseService service;

    @GetMapping("{month}/{year}")
    public ResponseEntity<List<DataDetailsExpense>> listByDate(@PathVariable int month, @PathVariable int year) {
        log.info("Listando despesas para o mês: {} e ano: {}", month, year);

        var expenses = this.service.listByMonthAndYear(month, year);

        log.info("Despesas encontradas: {}", expenses);

        return ResponseEntity.ok().body(expenses);
    }

}
