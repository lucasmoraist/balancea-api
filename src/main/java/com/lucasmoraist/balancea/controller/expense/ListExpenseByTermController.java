package com.lucasmoraist.balancea.controller.expense;

import com.lucasmoraist.balancea.domain.dto.DataListingExpense;
import com.lucasmoraist.balancea.service.ExpenseService;
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
@RequestMapping("/v2/expense")
@Tag(name = "Expense", description = "Operações relacionadas a despesas.")
@SecurityRequirement(name = "bearer-key")
@Slf4j
public class ListExpenseByTermController {

    @Autowired
    private ExpenseService service;

    @Operation(summary = "Lista despesas por termo.", description = "Lista todas as despesas que contém o termo fornecido.")
    @Parameter(name = "term", description = "Termo a ser buscado nas descrições das despesas.", required = true, example = "aluguel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Despesas encontradas.")
    })
    @GetMapping("search")
    public ResponseEntity<List<DataListingExpense>> listByTerm(@RequestParam String term) {
        log.info("Buscando despesas com o termo: {}", term);

        var expenses = this.service.listByDescription(term);

        log.info("Despesas encontradas: {}", expenses);

        return ResponseEntity.ok().body(expenses);
    }


}
