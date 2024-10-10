package com.lucasmoraist.balancea.controller.income;

import com.lucasmoraist.balancea.domain.dto.DataDetailsBudget;
import com.lucasmoraist.balancea.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/income")
public class GetIncomeController {

    @Autowired
    private IncomeService service;

    @GetMapping("{id}")
    public ResponseEntity<DataDetailsBudget> getIncome(@PathVariable Long id) {
        var income = this.service.findById(id);
        return ResponseEntity.ok().body(income);
    }

}
