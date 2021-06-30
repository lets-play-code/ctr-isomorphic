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
        if (from.equals(to)) {
            return Arrays.asList(toString(from));
        }
        if (fulfilledPrefix(from, to)) {
            return Arrays.asList(toPrefix(from));
        }
        return Stream.iterate(from, date -> date.plusDays(1))
                .limit(daysBetween(from, to))
                .map(DatePrefix::toString)
                .collect(Collectors.toList());
    }

    private static String toPrefix(LocalDate from) {
        String string = toString(from);
        return string.substring(0, string.length()-1);
    }

    private static boolean fulfilledPrefix(LocalDate from, LocalDate to) {
        String prefix = toPrefix(from);
        return toPrefix(to).equals(prefix)
                && !toPrefix(from.minusDays(1)).equals(prefix)
                && !toPrefix(to.plusDays(1)).equals(prefix);
    }

    private static long daysBetween(LocalDate from, LocalDate to) {
        return ChronoUnit.DAYS.between(from, to) + 1;
    }

    private static String toString(LocalDate from) {
        return from.format(FORMATTER);
    }
}
