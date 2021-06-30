package com.sap.ase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatePrefix {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<String> of(LocalDate from, LocalDate to) {
        if (fulfilledPrefix(from, to)) {
            return Arrays.asList(toPrefix(from));
        }
        return Stream.iterate(from, date -> date.plusDays(1))
                .limit(daysBetween(from, to))
                .map(DatePrefix::toString)
                .collect(Collectors.toList());
    }

    private static String toPrefix(LocalDate from) {
        return toString(from).substring(0, 9);
    }

    private static boolean fulfilledPrefix(LocalDate from, LocalDate to) {
        String dateString1 = toString(from);
        String dateString2 = toString(to);
        String dateStringBefore1 = toString(from.minusDays(1));
        String dateStringAfter2 = toString(to.plusDays(1));
//        if (toPrefix(from).equals(toPrefix(to) &&
//                !toPrefix(from.minusDays(1)).equals(toPrefix(to.plusDays(1)))))
        return daysBetween(from, to) == 10;
    }

    private static long daysBetween(LocalDate from, LocalDate to) {
        return ChronoUnit.DAYS.between(from, to) + 1;
    }

    private static String toString(LocalDate from) {
        return from.format(FORMATTER);
    }
}
