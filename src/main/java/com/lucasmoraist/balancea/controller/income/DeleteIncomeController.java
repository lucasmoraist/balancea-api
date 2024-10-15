package com.lucasmoraist.balancea.controller.income;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/income")
@Tag(name = "Income", description = "Operações relacionadas a receitas.")
@SecurityRequirement(name = "bearer-key")
@Slf4j
public class DeleteIncomeController {

    @Autowired
    private IncomeService service;

    @Operation(summary = "Exclui uma receita.", description = "Exclui uma receita com o ID fornecido.")
    @Parameter(name = "id", description = "ID da receita a ser excluída.", required = true, example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Receita excluída com sucesso."),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada.")
    })
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Iniciando a exclusão da receita com ID: {}", id);

        this.service.delete(id);

        log.info("Receita com ID {} excluída com sucesso.", id);
        return ResponseEntity.noContent().build();
    }


}
