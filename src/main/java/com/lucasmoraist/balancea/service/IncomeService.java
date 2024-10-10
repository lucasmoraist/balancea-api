package com.lucasmoraist.balancea.service;

import com.lucasmoraist.balancea.domain.dto.DataCreateIncome;
import com.lucasmoraist.balancea.domain.dto.DataDetailsIncome;
import com.lucasmoraist.balancea.domain.dto.DataListingIncome;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IncomeService {
    DataDetailsIncome save(DataCreateIncome data);
    Page<DataListingIncome> listAll(Pageable pageable);
    DataDetailsIncome findById(Long id);
    DataDetailsIncome update(Long id, DataCreateIncome data);
    void delete(Long id);
}
