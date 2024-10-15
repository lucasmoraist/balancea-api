package com.lucasmoraist.balancea.controller.expense;

import com.lucasmoraist.balancea.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/expense")
@Tag(name = "Expense", description = "Operações relacionadas a despesas.")
@Slf4j
public class DeleteExpenseController {

    @Autowired
    private ExpenseService service;

    @Operation(summary = "Exclui uma despesa.", description = "Exclui uma despesa com o ID fornecido.")
    @Parameter(name = "id", description = "ID da despesa a ser excluída.", required = true, example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Despesa excluída com sucesso."),
            @ApiResponse(responseCode = "404", description = "Despesa não encontrada.")
    })
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Iniciando a exclusão da despesa com ID: {}", id);

        this.service.delete(id);

        log.info("Despesa com ID: {} excluída com sucesso.", id);

        return ResponseEntity.noContent().build();
    }


}
