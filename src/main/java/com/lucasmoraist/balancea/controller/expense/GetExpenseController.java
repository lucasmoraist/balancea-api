package com.lucasmoraist.balancea.controller.expense;

import com.lucasmoraist.balancea.domain.dto.DataDetailsBudget;
import com.lucasmoraist.balancea.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/expense")
public class GetExpenseController {

    @Autowired
    private ExpenseService service;

    @GetMapping("{id}")
    public ResponseEntity<DataDetailsBudget> get(@PathVariable Long id) {
        var expense = this.service.findById(id);
        return ResponseEntity.ok().body(expense);
    }

}