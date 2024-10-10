package com.lucasmoraist.balancea.controller.expense;

import com.lucasmoraist.balancea.domain.dto.DataListingExpense;
import com.lucasmoraist.balancea.domain.dto.DataListingIncome;
import com.lucasmoraist.balancea.service.ExpenseService;
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
@RequestMapping("/v2/expense")
public class ListExpenseController {

    @Autowired
    private ExpenseService service;

    @GetMapping
    public ResponseEntity<Page<DataListingExpense>> list(
            @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC)
            Pageable pageable) {
        var expenses = this.service.listAll(pageable);
        return ResponseEntity.ok().body(expenses);
    }

}
