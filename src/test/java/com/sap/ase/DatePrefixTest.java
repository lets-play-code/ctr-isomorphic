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
            Arguments.of(LocalDate.of(2021, 6, 30), LocalDate.of(2021, 6, 30),
                    Arrays.asList("2021-06-30"), "Single day"),
            Arguments.of(LocalDate.of(2021, 6, 1), LocalDate.of(2021, 6, 3),
                    Arrays.asList("2021-06-01", "2021-06-02", "2021-06-03"), "list all"),
            Arguments.of(LocalDate.of(2021, 6, 1), LocalDate.of(2021, 6, 9),
                    Arrays.asList("2021-06-0"), "1st 9 days"),
            Arguments.of(LocalDate.of(2021, 6, 10), LocalDate.of(2021, 6, 19),
                    Arrays.asList("2021-06-1"), "2st 10 days")
        );
    }

    @ParameterizedTest(name = "{index}-{3}: {arguments}")
    @MethodSource("testDatas")
    void testDatePrefix(LocalDate from, LocalDate to, List<String> prefixes, String description) {
        assertEquals(prefixes, DatePrefix.of(from, to));
    }

}
