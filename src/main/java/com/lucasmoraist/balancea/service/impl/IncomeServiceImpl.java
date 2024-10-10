package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.dto.DataCreateBudgets;
import com.lucasmoraist.balancea.domain.dto.DataDetailsBudget;
import com.lucasmoraist.balancea.domain.dto.DataListing;
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
    public DataDetailsBudget save(DataCreateBudgets data) {
        List<Income> incomes = this.repository.findAll();
        var month = LocalDate.now().getMonth();

        for(var income : incomes) {
            if(income.getBudget().getDate().getMonth().equals(month)) {
                if (income.getBudget().getDescription().equals(data.description())) {
                    throw new RuntimeException("There is already an income for this budget in this month");
                }
            }
            break;
        }

        var income = new Income(data);

        this.repository.save(income);

        return new DataDetailsBudget(income);
    }

    @Override
    public Page<DataListing> listAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(d -> {
                    System.out.println(d);
                    return new DataListing(d.getId(), d.getBudget().getDescription(), d.getBudget().getAmount(), d.getBudget().getDate());
                });
    }

    @Override
    public DataDetailsBudget findById(Long id) {
        var income = this.getIncome(id);
        return new DataDetailsBudget(income);
    }

    @Override
    public DataDetailsBudget update(Long id, DataCreateBudgets data) {
        var income = this.getIncome(id);

        income.updateData(data);

        this.repository.save(income);

        return new DataDetailsBudget(income);
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
