package com.lucasmoraist.balancea.controller.income;

import com.lucasmoraist.balancea.domain.dto.DataListingIncome;
import com.lucasmoraist.balancea.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/income")
public class ListIncomeController {

    @Autowired
    private IncomeService service;

    @GetMapping
    public ResponseEntity<Page<DataListingIncome>> listIncomes(
            @PageableDefault(size = 10, sort = {"budget.date"}, direction = Sort.Direction.ASC)
            Pageable pageable) {
        var incomes = this.service.listAll(pageable);
        return ResponseEntity.ok().body(incomes);
    }
}
