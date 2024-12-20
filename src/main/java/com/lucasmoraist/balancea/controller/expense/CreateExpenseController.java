package com.lucasmoraist.balancea.controller.expense;

import com.lucasmoraist.balancea.domain.dto.DataCreateExpense;
import com.lucasmoraist.balancea.domain.dto.DataDetailsExpense;
import com.lucasmoraist.balancea.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/expense")
@Tag(name = "Expense", description = "Operações relacionadas a despesas.")
@SecurityRequirement(name = "bearer-key")
@Slf4j
public class CreateExpenseController {

    @Autowired
    private ExpenseService service;

    @Operation(summary = "Cria uma nova despesa.", description = "Cria uma nova despesa com os dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Despesa criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailsExpense> create(@RequestBody @Valid DataCreateExpense data) {
        log.info("Iniciando a criação de uma nova despesa.");

        log.debug("Dados da despesa a serem salvos: {}", data);

        var expense = this.service.save(data);

        log.info("Despesa criada com sucesso.");

        return ResponseEntity.ok().body(expense);
    }


}
