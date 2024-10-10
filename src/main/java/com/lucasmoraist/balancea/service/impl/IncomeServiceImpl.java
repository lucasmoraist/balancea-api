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

        return new DataDetailsIncome(income);
    }

    @Override
    public Page<DataListingIncome> listAll(Pageable pageable) {
        return null;
    }

    @Override
    public DataDetailsIncome findById(Long id) {
        return null;
    }

    @Override
    public DataDetailsIncome update(Long id, DataCreateIncome data) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
