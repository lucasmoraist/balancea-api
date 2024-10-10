package com.lucasmoraist.balancea.controller.income;

import com.lucasmoraist.balancea.domain.dto.DataCreateBudgets;
import com.lucasmoraist.balancea.domain.dto.DataDetailsBudget;
import com.lucasmoraist.balancea.service.IncomeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/income")
public class UpdateIncomeController {

    @Autowired
    private IncomeService service;

    @PutMapping("update/{id}")
    public ResponseEntity<DataDetailsBudget> update(@PathVariable Long id, @RequestBody @Valid DataCreateBudgets data) {
        var income = this.service.update(id, data);
        return ResponseEntity.ok().body(income);
    }

}
