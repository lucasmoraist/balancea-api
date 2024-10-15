package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.dto.DataCreateIncome;
import com.lucasmoraist.balancea.domain.dto.DataDetailsIncome;
import com.lucasmoraist.balancea.domain.dto.DataListingIncome;
import com.lucasmoraist.balancea.domain.entity.Income;
import com.lucasmoraist.balancea.exceptions.DuplicateBadgetException;
import com.lucasmoraist.balancea.exceptions.ResourceNotFoundException;
import com.lucasmoraist.balancea.repository.IncomeRepository;
import com.lucasmoraist.balancea.service.IncomeService;
import com.lucasmoraist.balancea.validations.ValidateMonthAndYear;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository repository;
    private final ValidateMonthAndYear validateMonthAndYear;

    @Override
    public DataDetailsIncome save(DataCreateIncome data) {
        log.info("Iniciando o salvamento da renda com os dados: {}", data);
        this.validate(data);

        var income = new Income(data);
        this.repository.save(income);
        log.info("Renda salva com sucesso: {}", income);

        return new DataDetailsIncome(income);
    }

    @Override
    public Page<DataListingIncome> listAll(Pageable pageable) {
        log.info("Listando todas as rendas com paginação: {}", pageable);
        return this.repository.findAll(pageable)
                .map(DataListingIncome::new);
    }

    @Override
    public List<DataListingIncome> listByDescription(String term) {
        log.info("Buscando rendas com a descrição: {}", term);
        return this.repository.listIncomeByTerm(term)
                .stream()
                .map(DataListingIncome::new)
                .toList();
    }

    @Override
    public List<DataDetailsIncome> listByMonthAndYear(int month, int year) {
        log.info("Listando rendas para o mês: {} e ano: {}", month, year);
        this.validateMonthAndYear.validate(month, year);

        var incomes = this.repository.findAll();
        return incomes.stream()
                .filter(i -> i.getBudget().getDate().getMonthValue() == month && i.getBudget().getDate().getYear() == year)
                .map(DataDetailsIncome::new)
                .toList();
    }

    @Override
    public DataDetailsIncome findById(Long id) {
        log.info("Buscando renda com ID: {}", id);
        var income = this.getIncome(id);
        return new DataDetailsIncome(income);
    }

    @Override
    public DataDetailsIncome update(Long id, DataCreateIncome data) {
        log.info("Atualizando renda com ID: {}", id);
        var income = this.getIncome(id);

        income.updateData(data);
        this.repository.save(income);
        log.info("Renda atualizada com sucesso: {}", income);

        return new DataDetailsIncome(income);
    }

    @Override
    public void delete(Long id) {
        log.info("Removendo renda com ID: {}", id);
        var income = this.getIncome(id);
        this.repository.delete(income);
        log.info("Renda removida com sucesso: {}", income);
    }

    private Income getIncome(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Renda não encontrada com ID: {}", id);
                    return new ResourceNotFoundException("Income not found");
                });
    }

    private void validate(DataCreateIncome data) {
        var exists = this.repository.existsByDescriptionAndMonth(data.description(), data.date().getMonthValue());
        if (exists) {
            log.error("A renda já existe para o mês: {} com a descrição: {}", data.date().getMonthValue(), data.description());
            throw new DuplicateBadgetException("Income already exists for this month");
        }
        log.info("Validação de renda concluída sem duplicatas para o mês: {}", data.date().getMonthValue());
    }
}
