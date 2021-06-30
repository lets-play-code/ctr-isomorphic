package com.sap.ase;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DatePrefixTest {
    @Test
    void should_be_date_string_for_single_date() {
        LocalDate date = LocalDate.of(2021, 6, 30);
        assertEquals(Arrays.asList("2021-06-30"), DatePrefix.of(date, date));
    }

}
