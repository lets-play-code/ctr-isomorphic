package com.sap.ase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatePrefix {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<String> of(LocalDate from, LocalDate to) {
        if (from.equals(to)) {
            return Arrays.asList(toDateString(from));
        }
        List<String> prefixes = new ArrayList<>();
        for (LocalDate date = from; !date.isAfter(to); date = date.plusDays(1)) {
            prefixes.add(toDateString(date));
        }
        return prefixes;
    }

    private static String toDateString(LocalDate from) {
        return from.format(FORMATTER);
    }
}
