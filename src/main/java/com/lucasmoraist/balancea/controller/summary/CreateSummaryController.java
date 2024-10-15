package com.lucasmoraist.balancea.controller.summary;

import com.lucasmoraist.balancea.domain.dto.DataCreateSummary;
import com.lucasmoraist.balancea.service.SummaryService;
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

@RestController
@RequestMapping("/v2/summary")
@Tag(name = "Summary", description = "Operações relacionadas a resumos")
@Slf4j
public class CreateSummaryController {

    @Autowired
    private SummaryService service;

    @Operation(summary = "Cria um resumo para o mês e ano informados", description = "Cria um resumo para o mês e ano informados")
    @Parameters(value = {
            @Parameter(name = "month", description = "Mês para o qual o resumo será criado", required = true),
            @Parameter(name = "year", description = "Ano para o qual o resumo será criado", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resumo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Mês ou ano inválidos"),
    })
    @GetMapping("{month}/{year}")
    public ResponseEntity<DataCreateSummary> create(@PathVariable int month, @PathVariable int year) {
        log.info("Iniciando criação de resumo para o mês: {} e ano: {}", month, year);

        var response = this.service.createSummary(month, year);

        log.info("Resumo criado com sucesso para o mês: {} e ano: {}", month, year);
        return ResponseEntity.ok().body(response);
    }


}
