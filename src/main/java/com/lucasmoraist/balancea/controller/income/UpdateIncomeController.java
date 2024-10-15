package com.lucasmoraist.balancea.controller.income;

import com.lucasmoraist.balancea.domain.dto.DataCreateIncome;
import com.lucasmoraist.balancea.domain.dto.DataDetailsIncome;
import com.lucasmoraist.balancea.service.IncomeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/income")
@Tag(name = "Income")
@Slf4j
public class UpdateIncomeController {

    @Autowired
    private IncomeService service;

    @PutMapping("update/{id}")
    public ResponseEntity<DataDetailsIncome> update(@PathVariable Long id, @RequestBody @Valid DataCreateIncome data) {
        log.info("Atualizando receita com ID: {}", id);
        log.debug("Dados da receita a serem atualizados: {}", data);

        var income = this.service.update(id, data);

        log.info("Receita com ID: {} atualizada com sucesso.", id);

        return ResponseEntity.ok().body(income);
    }


}
