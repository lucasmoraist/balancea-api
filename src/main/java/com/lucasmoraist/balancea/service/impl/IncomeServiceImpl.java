package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.dto.DataCreateIncome;
import com.lucasmoraist.balancea.domain.dto.DataDetailsIncome;
import com.lucasmoraist.balancea.domain.dto.DataListingIncome;
import com.lucasmoraist.balancea.domain.entity.Income;
import com.lucasmoraist.balancea.repository.IncomeRepository;
import com.lucasmoraist.balancea.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeRepository repository;

    @Override
    public DataDetailsIncome save(DataCreateIncome data) {
        var exists = this.repository.existsByDescriptionAndMonth(data.description(), data.date().getMonthValue());

        if (exists) {
            throw new RuntimeException("Income already exists for this month");
        }

        var income = new Income(data);

        this.repository.save(income);

        return new DataDetailsIncome(income);
    }

    @Override
    public Page<DataListingIncome> listAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(d -> {
                    System.out.println(d);
                    return new DataListingIncome(d.getId(), d.getBudget().getDescription(), d.getBudget().getAmount(), d.getBudget().getDate());
                });
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

        if (month > 12 || month < 1) {
            throw new RuntimeException("Invalid month");
        }

        if (year < 1000 || year > 9999) {
            throw new RuntimeException("Invalid year");
        }

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
                .orElseThrow(() -> new RuntimeException("Income not found"));
    }

}
