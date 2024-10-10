package com.lucasmoraist.balancea.validations;

import com.lucasmoraist.balancea.exceptions.InvalidDateException;
import org.springframework.stereotype.Component;

@Component
public class ValidateMonthAndYear {

    public void validate(int month, int year) {
        if (month > 12 || month < 1) {
            throw new InvalidDateException("Invalid month");
        }

        if (year < 1000 || year > 9999) {
            throw new InvalidDateException("Invalid year");
        }
    }
}
