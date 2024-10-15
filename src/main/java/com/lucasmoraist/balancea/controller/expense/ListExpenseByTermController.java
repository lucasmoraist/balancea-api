package com.lucasmoraist.balancea.controller.expense;

import com.lucasmoraist.balancea.domain.dto.DataListingExpense;
import com.lucasmoraist.balancea.service.ExpenseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v2/expense")
@Tag(name = "Expense")
@Slf4j
public class ListExpenseByTermController {

    @Autowired
    private ExpenseService service;

    @GetMapping("search")
    public ResponseEntity<List<DataListingExpense>> listByTerm(@RequestParam String term) {
        log.info("Buscando despesas com o termo: {}", term);

        var expenses = this.service.listByDescription(term);

        log.info("Despesas encontradas: {}", expenses);

        return ResponseEntity.ok().body(expenses);
    }


}
