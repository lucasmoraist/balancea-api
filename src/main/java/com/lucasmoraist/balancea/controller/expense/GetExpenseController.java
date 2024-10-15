package com.lucasmoraist.balancea.controller.expense;

import com.lucasmoraist.balancea.domain.dto.DataDetailsExpense;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/expense")
@Tag(name = "Expense", description = "Operações relacionadas a despesas.")
@SecurityRequirement(name = "bearer-key")
@Slf4j
public class GetExpenseController {

    @Autowired
    private ExpenseService service;

    @Operation(summary = "Busca uma despesa.", description = "Busca uma despesa com o ID fornecido.")
    @Parameter(name = "id", description = "ID da despesa a ser buscada.", required = true, example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Despesa encontrada."),
            @ApiResponse(responseCode = "404", description = "Despesa não encontrada.")
    })
    @GetMapping("{id}")
    public ResponseEntity<DataDetailsExpense> get(@PathVariable Long id) {
        log.info("Buscando despesa com ID: {}", id);

        var expense = this.service.findById(id);

        log.info("Despesa encontrada: {}", expense);

        return ResponseEntity.ok().body(expense);
    }


}
