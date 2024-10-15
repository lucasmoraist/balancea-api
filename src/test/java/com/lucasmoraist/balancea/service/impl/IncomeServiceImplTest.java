package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.dto.DataCreateIncome;
import com.lucasmoraist.balancea.domain.dto.DataDetailsIncome;
import com.lucasmoraist.balancea.domain.dto.DataListingIncome;
import com.lucasmoraist.balancea.domain.entity.Budget;
import com.lucasmoraist.balancea.domain.entity.Income;
import com.lucasmoraist.balancea.exceptions.DuplicateBadgetException;
import com.lucasmoraist.balancea.repository.IncomeRepository;
import com.lucasmoraist.balancea.validations.ValidateMonthAndYear;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class IncomeServiceImplTest {

    @InjectMocks
    private IncomeServiceImpl service;
    @Mock
    private IncomeRepository repository;

    private DataCreateIncome dataCreate;
    private DataDetailsIncome dataDetails;

    @BeforeEach
    void setup() {

        this.repository.deleteAll();

        this.dataCreate = new DataCreateIncome(
                "description",
                BigDecimal.valueOf(1000),
                LocalDate.of(2021, 1, 1)
        );

        var income = new Income(dataCreate);
        this.repository.save(income);

        this.dataDetails = new DataDetailsIncome(income);
    }

    @Test
    @DisplayName("save: should save an income")
    void case01() {
        assertEquals(dataDetails, this.service.save(dataCreate));
    }
}