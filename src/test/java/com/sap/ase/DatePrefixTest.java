package com.sap.ase;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DatePrefixTest {
    @Test
    void should_be_date_string_for_single_date() {
        assertEquals("2021-06-30", DatePrefix.of(LocalDate.of(2021,6,30)));
    }

}
