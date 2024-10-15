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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/income")
@Tag(name = "Income", description = "Operações relacionadas a receitas.")
@SecurityRequirement(name = "bearer-key")
@Slf4j
public class ListIncomeController {

    @Autowired
    private IncomeService service;

    @Operation(summary = "Lista receitas.", description = "Lista todas as receitas com paginação.")
    @Parameter(name = "page", description = "Número da página.", example = "0")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receitas encontradas.")
    })
    @GetMapping
    public ResponseEntity<Page<DataListingIncome>> listIncomes(
            @PageableDefault(size = 10, sort = {"budget.date"}, direction = Sort.Direction.ASC)
            Pageable pageable) {

        log.info("Listando receitas com parâmetros de paginação: page = {}, size = {}, sort = {}",
                pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());

        var incomes = this.service.listAll(pageable);

        log.info("Total de receitas encontradas: {}", incomes.getTotalElements());

        return ResponseEntity.ok().body(incomes);
    }

}
