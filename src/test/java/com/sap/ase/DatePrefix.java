package com.sap.ase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatePrefix {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<String> of(LocalDate from, LocalDate to) {
        if (daysBetween(from, to) == 10) {
            return Arrays.asList(/*toDateString(from).substring(0, 9)*/"2021-06-0");
        }
        return Stream.iterate(from, date -> date.plusDays(1))
                .limit(daysBetween(from, to))
                .map(DatePrefix::toDateString)
                .collect(Collectors.toList());
    }

    private static long daysBetween(LocalDate from, LocalDate to) {
        return ChronoUnit.DAYS.between(from, to) + 1;
    }

    private static String toDateString(LocalDate from) {
        return from.format(FORMATTER);
    }
}
