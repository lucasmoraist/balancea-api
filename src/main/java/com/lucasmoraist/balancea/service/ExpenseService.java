package com.lucasmoraist.balancea.service;

import com.lucasmoraist.balancea.domain.dto.DataCreateBudgets;
import com.lucasmoraist.balancea.domain.dto.DataDetailsBudget;
import com.lucasmoraist.balancea.domain.dto.DataListing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenseService {
    DataDetailsBudget save(DataCreateBudgets data);
    Page<DataListing> listAll(Pageable pageable);
    DataDetailsBudget findById(Long id);
    DataDetailsBudget update(Long id, DataCreateBudgets data);
    void delete(Long id);
}
