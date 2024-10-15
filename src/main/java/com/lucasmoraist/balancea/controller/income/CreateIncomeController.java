package com.lucasmoraist.balancea.controller.income;

import com.lucasmoraist.balancea.domain.dto.DataCreateIncome;
import com.lucasmoraist.balancea.domain.dto.DataDetailsIncome;
import com.lucasmoraist.balancea.service.IncomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@Tag(name = "Income", description = "Operações relacionadas a receitas.")
@SecurityRequirement(name = "bearer-key")
@Slf4j
public class CreateIncomeController {

    @Autowired
    private IncomeService service;

    @Operation(summary = "Cria uma nova receita.", description = "Cria uma nova receita com os dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receita criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PostMapping
    public ResponseEntity<DataDetailsIncome> create(@RequestBody @Valid DataCreateIncome data) {
        log.info("Iniciando criação de receita com os dados: {}", data);

        var income = this.service.save(data);

        log.info("Receita criada com sucesso: {}", income);
        return ResponseEntity.ok().body(income);
    }


}
