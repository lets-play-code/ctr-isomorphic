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

    @Test
    void should_list_all_date_if_date_range_dont_cover_a_prefix() {
        LocalDate from = LocalDate.of(2021, 6, 1);
        LocalDate to = LocalDate.of(2021, 6, 3);
        assertEquals(Arrays.asList("2021-06-01", "2021-06-02", "2021-06-03"), DatePrefix.of(from, to));
    }

    @Test
    void should_be_prefix_of_10_days_if_date_range_fully_covered() {
        LocalDate from = LocalDate.of(2021, 6, 1);
        LocalDate to = LocalDate.of(2021, 6, 10);
        assertEquals(Arrays.asList("2021-06-0"), DatePrefix.of(from, to));
    }
}
