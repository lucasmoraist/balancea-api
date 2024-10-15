package com.lucasmoraist.balancea.controller.income;

import com.lucasmoraist.balancea.domain.dto.DataCreateIncome;
import com.lucasmoraist.balancea.domain.dto.DataDetailsIncome;
import com.lucasmoraist.balancea.service.IncomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/income")
@Tag(name = "Income", description = "Operações relacionadas a receitas.")
@Slf4j
public class UpdateIncomeController {

    @Autowired
    private IncomeService service;

    @Operation(summary = "Atualiza uma receita.", description = "Atualiza uma receita com o ID fornecido.")
    @Parameter(name = "id", description = "ID da receita a ser atualizada.", required = true, example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receita atualizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.")
    })
    @PutMapping("update/{id}")
    public ResponseEntity<DataDetailsIncome> update(@PathVariable Long id, @RequestBody @Valid DataCreateIncome data) {
        log.info("Atualizando receita com ID: {}", id);
        log.debug("Dados da receita a serem atualizados: {}", data);

        var income = this.service.update(id, data);

        log.info("Receita com ID: {} atualizada com sucesso.", id);

        return ResponseEntity.ok().body(income);
    }


}
