package com.lucasmoraist.balancea.controller.expense;

import com.lucasmoraist.balancea.domain.dto.DataCreateExpense;
import com.lucasmoraist.balancea.domain.dto.DataCreateIncome;
import com.lucasmoraist.balancea.domain.dto.DataDetailsExpense;
import com.lucasmoraist.balancea.domain.dto.DataDetailsIncome;
import com.lucasmoraist.balancea.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/expense")
public class UpdateExpenseController {

    @Autowired
    private ExpenseService service;

    @PutMapping("update/{id}")
    public ResponseEntity<DataDetailsExpense> update(
            @PathVariable Long id,
            @RequestBody @Valid DataCreateExpense data) {
        var expense = this.service.update(id, data);
        return ResponseEntity.ok().body(expense);
    }

}
