package com.lucasmoraist.balancea.controller.income;

import com.lucasmoraist.balancea.domain.dto.DataDetailsIncome;
import com.lucasmoraist.balancea.service.IncomeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/income")
@Tag(name = "Income")
@Slf4j
public class GetIncomeController {

    @Autowired
    private IncomeService service;

    @GetMapping("{id}")
    public ResponseEntity<DataDetailsIncome> getIncome(@PathVariable Long id) {
        log.info("Buscando a receita com ID: {}", id);

        var income = this.service.findById(id);

        log.info("Receita encontrada: {}", income);
        return ResponseEntity.ok().body(income);
    }


}
