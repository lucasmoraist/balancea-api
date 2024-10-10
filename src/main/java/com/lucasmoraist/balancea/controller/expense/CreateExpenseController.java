package com.lucasmoraist.balancea.controller.expense;

import com.lucasmoraist.balancea.domain.dto.DataCreateBudgets;
import com.lucasmoraist.balancea.domain.dto.DataDetailsBudget;
import com.lucasmoraist.balancea.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/expense")
public class CreateExpenseController {

    @Autowired
    private ExpenseService service;

    @PostMapping
    public ResponseEntity<DataDetailsBudget> create(@RequestBody @Valid DataCreateBudgets data) {
        var expense = this.service.save(data);
        return ResponseEntity.ok().body(expense);
    }

}
