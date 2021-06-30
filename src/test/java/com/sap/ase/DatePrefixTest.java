package com.sap.ase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DatePrefixTest {
    private static Stream<Arguments> testDatas() {
        return Stream.of(
            Arguments.of(LocalDate.of(2021, 6, 30), LocalDate.of(2021, 6, 30), Arrays.asList("2021-06-30"), "Single day")
        );
    }

    @ParameterizedTest(name = "{3}: {arguments}")
    @MethodSource("testDatas")
    void testDatePrefix(LocalDate from, LocalDate to, List<String> prefixes) {
        assertEquals(prefixes, DatePrefix.of(from, to));
    }

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
        LocalDate to = LocalDate.of(2021, 6, 9);
        assertEquals(Arrays.asList("2021-06-0"), DatePrefix.of(from, to));
    }
}
