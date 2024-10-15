package com.lucasmoraist.balancea.controller.income;

import com.lucasmoraist.balancea.domain.dto.DataListingIncome;
import com.lucasmoraist.balancea.service.IncomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@Tag(name = "Income", description = "Operações relacionadas a receitas.")
@SecurityRequirement(name = "bearer-key")
@Slf4j
public class ListIncomeByTermController {

    @Autowired
    private IncomeService service;

    @Operation(summary = "Lista receitas por termo.", description = "Lista todas as receitas que contém o termo fornecido.")
    @Parameter(name = "term", description = "Termo a ser buscado nas descrições das receitas.", required = true, example = "aluguel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receitas encontradas.")
    })
    @GetMapping("search")
    public ResponseEntity<List<DataListingIncome>> listByTerm(@RequestParam String term) {
        log.info("Buscando receitas com o termo: {}", term);

        var incomes = this.service.listByDescription(term);

        log.info("Receitas encontradas: {}", incomes.size());
        return ResponseEntity.ok().body(incomes);
    }


}
