package com.xp.tcr;

import com.xp.tcr.DatePrefix;
import org.junit.jupiter.api.Assertions;
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
                    Arrays.asList("2021-06-1"), "2nd 10 days"),
            Arguments.of(LocalDate.of(2021, 6, 20), LocalDate.of(2021, 6, 29),
                    Arrays.asList("2021-06-2"), "3rd 10 days"),
            Arguments.of(LocalDate.of(2021, 7, 30), LocalDate.of(2021, 7, 31),
                    Arrays.asList("2021-07-3"), "day 30 & 31"),
            Arguments.of(LocalDate.of(2021, 2, 20), LocalDate.of(2021, 2, 28),
                    Arrays.asList("2021-02-2"), "normal year Feb 2x"),
            Arguments.of(LocalDate.of(2020, 2, 20), LocalDate.of(2020, 2, 29),
                    Arrays.asList("2020-02-2"), "leap year Feb 2x"),
            Arguments.of(LocalDate.of(2020, 2, 20), LocalDate.of(2020, 2, 28),
                    Arrays.asList("2020-02-20","2020-02-21","2020-02-22","2020-02-23","2020-02-24"
                            ,"2020-02-25","2020-02-26","2020-02-27","2020-02-28")
                    , "leap year Feb not cover 29"),
            Arguments.of(LocalDate.of(2020, 2, 1), LocalDate.of(2020, 2, 29),
                    Arrays.asList("2020-02-"), "prefix for a month"),
            Arguments.of(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 9, 30),
                    Arrays.asList("2020-0"), "1st 9 months"),
            Arguments.of(LocalDate.of(2020, 10, 1), LocalDate.of(2020, 12, 31),
                    Arrays.asList("2020-1"), "Oct, Nov, Dec"),
            Arguments.of(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 12, 31),
                    Arrays.asList("2020-"), "prefix for whole year"),
            Arguments.of(LocalDate.of(2021, 6, 10), LocalDate.of(2021, 6, 20),
                    Arrays.asList("2021-06-1", "2021-06-20"), "prefix and a single day"),
            Arguments.of(LocalDate.of(2021, 6, 1), LocalDate.of(2021, 6, 29),
                    Arrays.asList("2021-06-0", "2021-06-1", "2021-06-2"), "3 prefixes from 1st"),
            Arguments.of(LocalDate.of(2021, 5, 30), LocalDate.of(2021, 6, 12),
                    Arrays.asList("2021-05-3", "2021-06-0", "2021-06-10", "2021-06-11", "2021-06-12"), "2 prefixes cross month and 3 days"),
            Arguments.of(LocalDate.of(2021, 6, 17), LocalDate.of(2021, 6, 29),
                    Arrays.asList("2021-06-17","2021-06-18","2021-06-19", "2021-06-2"), "single days and prefix"),
            Arguments.of(LocalDate.of(2021, 6, 19), LocalDate.of(2021, 6, 29),
                    Arrays.asList("2021-06-19", "2021-06-2"), "single day and prefix"),
            Arguments.of(LocalDate.of(2020, 12, 28), LocalDate.of(2022, 11, 2),
                    Arrays.asList("2020-12-28", "2020-12-29", "2020-12-3", "2021-","2022-0", "2022-10-", "2022-11-01", "2022-11-02"), "days, 10-days, year, 10-monthes, month, days"),
            Arguments.of(LocalDate.of(2021, 6, 20), LocalDate.of(2021, 7, 9),
                    Arrays.asList("2021-06-2","2021-06-30","2021-07-0"), "single day is no prefix")
        );
    }

    @ParameterizedTest(name = "{index}-{3}: {arguments}")
    @MethodSource("testDatas")
    void testDatePrefix(LocalDate from, LocalDate to, List<String> prefixes, String description) {
        Assertions.assertEquals(prefixes, DatePrefix.of(from, to));
    }

}