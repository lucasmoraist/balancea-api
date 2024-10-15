package com.lucasmoraist.balancea.controller.income;

import com.lucasmoraist.balancea.domain.dto.DataCreateIncome;
import com.lucasmoraist.balancea.domain.dto.DataDetailsIncome;
import com.lucasmoraist.balancea.service.IncomeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/income")
@Tag(name = "Income")
@Slf4j
public class CreateIncomeController {

    @Autowired
    private IncomeService service;

    @PostMapping
    public ResponseEntity<DataDetailsIncome> create(@RequestBody @Valid DataCreateIncome data) {
        log.info("Iniciando criação de receita com os dados: {}", data);

        var income = this.service.save(data);

        log.info("Receita criada com sucesso: {}", income);
        return ResponseEntity.ok().body(income);
    }


}
