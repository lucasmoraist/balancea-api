package com.lucasmoraist.balancea.service;

import com.lucasmoraist.balancea.domain.dto.DataCreateSummary;

public interface SummaryService {
    DataCreateSummary createSummary(int month, int year);
}
