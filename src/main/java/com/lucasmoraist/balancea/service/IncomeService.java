package com.lucasmoraist.balancea.service;

import com.lucasmoraist.balancea.domain.dto.DataCreateIncome;
import com.lucasmoraist.balancea.domain.dto.DataDetailsIncome;
import com.lucasmoraist.balancea.domain.dto.DataListingIncome;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IncomeService {
    DataDetailsIncome save(DataCreateIncome data);
    Page<DataListingIncome> listAll(Pageable pageable);
    List<DataListingIncome> listByDescription(String term);
    List<DataDetailsIncome> listByMonthAndYear(int month, int year);
    DataDetailsIncome findById(Long id);
    DataDetailsIncome update(Long id, DataCreateIncome data);
    void delete(Long id);
}
