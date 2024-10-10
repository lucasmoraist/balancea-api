package com.lucasmoraist.balancea.controller.income;

import com.lucasmoraist.balancea.domain.dto.DataDetailsIncome;
import com.lucasmoraist.balancea.service.IncomeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v2/income")
@Tag(name = "Income")
public class ListIncomeByDate {

    @Autowired
    private IncomeService service;

    @GetMapping("{month}/{year}")
    public ResponseEntity<List<DataDetailsIncome>> listByDate(@PathVariable int month, @PathVariable int year) {
        var incomes = this.service.listByMonthAndYear(month, year);
        return ResponseEntity.ok().body(incomes);
    }

}
