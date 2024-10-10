package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.dto.DataCreateIncome;
import com.lucasmoraist.balancea.domain.dto.DataDetailsIncome;
import com.lucasmoraist.balancea.domain.dto.DataListingIncome;
import com.lucasmoraist.balancea.domain.entity.Income;
import com.lucasmoraist.balancea.exceptions.DuplicateBadgetException;
import com.lucasmoraist.balancea.exceptions.InvalidDateException;
import com.lucasmoraist.balancea.exceptions.ResourceNotFoundException;
import com.lucasmoraist.balancea.repository.IncomeRepository;
import com.lucasmoraist.balancea.service.IncomeService;
import com.lucasmoraist.balancea.validations.ValidateMonthAndYear;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository repository;
    private final ValidateMonthAndYear validateMonthAndYear;

    @Override
    public DataDetailsIncome save(DataCreateIncome data) {
        this.validate(data);

        var income = new Income(data);

        this.repository.save(income);

        return new DataDetailsIncome(income);
    }

    @Override
    public Page<DataListingIncome> listAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(DataListingIncome::new);
    }

    @Override
    public List<DataListingIncome> listByDescription(String term) {
        return this.repository.listIncomeByTerm(term)
                .stream()
                .map(DataListingIncome::new)
                .toList();
    }

    @Override
    public List<DataDetailsIncome> listByMonthAndYear(int month, int year) {
        var incomes = this.repository.findAll();

        this.validateMonthAndYear.validate(month, year);

        return incomes.stream()
                .filter(i -> i.getBudget().getDate().getMonthValue() == month && i.getBudget().getDate().getYear() == year)
                .map(DataDetailsIncome::new)
                .toList()
                ;
    }

    @Override
    public DataDetailsIncome findById(Long id) {
        var income = this.getIncome(id);
        return new DataDetailsIncome(income);
    }

    @Override
    public DataDetailsIncome update(Long id, DataCreateIncome data) {
        var income = this.getIncome(id);

        income.updateData(data);

        this.repository.save(income);

        return new DataDetailsIncome(income);
    }

    @Override
    public void delete(Long id) {
        var income = this.getIncome(id);
        this.repository.delete(income);
    }

    private Income getIncome(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Income not found"));
    }

    private void validate(DataCreateIncome data) {
        var exists = this.repository.existsByDescriptionAndMonth(data.description(), data.date().getMonthValue());

        if (exists) {
            throw new DuplicateBadgetException("Income already exists for this month");
        }
    }
}
