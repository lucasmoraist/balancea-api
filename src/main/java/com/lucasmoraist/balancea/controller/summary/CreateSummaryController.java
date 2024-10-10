package com.lucasmoraist.balancea.controller.summary;

import com.lucasmoraist.balancea.domain.dto.DataCreateSummary;
import com.lucasmoraist.balancea.service.SummaryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/summary")
@Tag(name = "Summary")
public class CreateSummaryController {

    @Autowired
    private SummaryService service;

    @GetMapping("{month}/{year}")
    public ResponseEntity<DataCreateSummary> create(@PathVariable int month, @PathVariable int year) {
        var response = this.service.createSummary(month, year);
        return ResponseEntity.ok().body(response);
    }

}
