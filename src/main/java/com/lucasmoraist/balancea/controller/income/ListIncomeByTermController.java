package com.lucasmoraist.balancea.controller.income;

import com.lucasmoraist.balancea.domain.dto.DataListingIncome;
import com.lucasmoraist.balancea.service.IncomeService;
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
@RequestMapping("/v2/income")
@Tag(name = "Income")
@Slf4j
public class ListIncomeByTermController {

    @Autowired
    private IncomeService service;

    @GetMapping("search")
    public ResponseEntity<List<DataListingIncome>> listByTerm(@RequestParam String term) {
        log.info("Buscando receitas com o termo: {}", term);

        var incomes = this.service.listByDescription(term);

        log.info("Receitas encontradas: {}", incomes.size());
        return ResponseEntity.ok().body(incomes);
    }


}
