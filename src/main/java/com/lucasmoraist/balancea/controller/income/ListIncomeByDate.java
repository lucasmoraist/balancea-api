package com.lucasmoraist.balancea.controller.income;

import com.lucasmoraist.balancea.domain.dto.DataDetailsIncome;
import com.lucasmoraist.balancea.service.IncomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v2/income")
@Tag(name = "Income", description = "Operações relacionadas a receitas.")
@Slf4j
public class ListIncomeByDate {

    @Autowired
    private IncomeService service;

    @Operation(summary = "Lista receitas por mês e ano.", description = "Lista todas as receitas para o mês e ano fornecidos.")
    @Parameters(value = {
            @Parameter(name = "month", description = "Mês da receita.", required = true, example = "1"),
            @Parameter(name = "year", description = "Ano da receita.", required = true, example = "2021")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receitas encontradas."),
            @ApiResponse(responseCode = "404", description = "Receitas não encontradas."),
            @ApiResponse(responseCode = "400", description = "Mês ou ano inválidos.")
    })
    @GetMapping("{month}/{year}")
    public ResponseEntity<List<DataDetailsIncome>> listByDate(@PathVariable int month, @PathVariable int year) {
        log.info("Buscando receitas para o mês: {} e ano: {}", month, year);

        var incomes = this.service.listByMonthAndYear(month, year);

        log.info("Receitas encontradas: {}", incomes.size());
        return ResponseEntity.ok().body(incomes);
    }


}
