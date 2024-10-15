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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/expense")
@Tag(name = "Expense", description = "Operações relacionadas a despesas.")
@SecurityRequirement(name = "bearer-key")
@Slf4j
public class ListExpenseController {

    @Autowired
    private ExpenseService service;

    @Operation(summary = "Lista despesas.", description = "Lista todas as despesas com paginação.")
    @Parameter(name = "page", description = "Número da página.", example = "0")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Despesas encontradas.")
    })
    @GetMapping
    public ResponseEntity<Page<DataListingExpense>> list(
            @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC)
            Pageable pageable) {

        log.info("Listando despesas com paginação: página={}, tamanho={}", pageable.getPageNumber(), pageable.getPageSize());

        var expenses = this.service.listAll(pageable);

        log.info("Total de despesas encontradas: {}", expenses.getTotalElements());

        return ResponseEntity.ok().body(expenses);
    }


}
