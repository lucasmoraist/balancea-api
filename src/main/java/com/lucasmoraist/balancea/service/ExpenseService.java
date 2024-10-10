package com.lucasmoraist.balancea.service;

import com.lucasmoraist.balancea.domain.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExpenseService {
    DataDetailsExpense save(DataCreateExpense data);
    Page<DataListingExpense> listAll(Pageable pageable);
    List<DataListingExpense> listByDescription(String term);
    List<DataDetailsExpense> listByMonthAndYear(int month, int year);
    DataDetailsExpense findById(Long id);
    DataDetailsExpense update(Long id, DataCreateExpense data);
    void delete(Long id);
}
