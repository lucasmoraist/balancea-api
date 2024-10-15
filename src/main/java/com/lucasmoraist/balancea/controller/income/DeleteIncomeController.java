package com.lucasmoraist.balancea.controller.income;

import com.lucasmoraist.balancea.service.IncomeService;
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
@Tag(name = "Income")
@Slf4j
public class DeleteIncomeController {

    @Autowired
    private IncomeService service;

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Iniciando a exclusão da receita com ID: {}", id);

        this.service.delete(id);

        log.info("Receita com ID {} excluída com sucesso.", id);
        return ResponseEntity.noContent().build();
    }


}
