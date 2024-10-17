package com.lucasmoraist.balancea.validations.impl;

import com.lucasmoraist.balancea.exceptions.InvalidDateException;
import com.lucasmoraist.balancea.validations.ValidateMonthAndYear;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidateMonthAndYearImpl implements ValidateMonthAndYear {

    @Override
    public void validate(int month, int year) {
        if (month > 12 || month < 1) {
            log.warn("Validação falhou: mês inválido {}", month);
            throw new InvalidDateException("Invalid month");
        }

        if (year < 1000 || year > 9999) {
            log.warn("Validação falhou: ano inválido {}", year);
            throw new InvalidDateException("Invalid year");
        }

        log.info("Validação bem-sucedida: mês {} e ano {}", month, year);
    }

}
