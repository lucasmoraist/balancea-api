package com.lucasmoraist.balancea.validations;

import com.lucasmoraist.balancea.exceptions.InvalidDateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ValidateMonthAndYearTest {

    @Autowired
    private ValidateMonthAndYear validateMonthAndYear;

    @Test
    @DisplayName("Should throw exception when month is less than 1")
    void case01() {
        assertThrows(InvalidDateException.class, () -> this.validateMonthAndYear.validate(0, 2021));
    }

    @Test
    @DisplayName("Should throw exception when month is greater than 12")
    void case02() {
        assertThrows(InvalidDateException.class, () -> this.validateMonthAndYear.validate(13, 2021));
    }

    @Test
    @DisplayName("Should throw exception when year is less than 1000")
    void case03() {
        assertThrows(InvalidDateException.class, () -> this.validateMonthAndYear.validate(1, 999));
    }

    @Test
    @DisplayName("Should throw exception when year is greater than 9999")
    void case04() {
        assertThrows(InvalidDateException.class, () -> this.validateMonthAndYear.validate(1, 10000));
    }
}