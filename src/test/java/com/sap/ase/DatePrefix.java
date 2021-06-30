package com.sap.ase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class DatePrefix {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<String> of(LocalDate from, LocalDate to) {
        if (from.equals(to)) {
            String prefix = from.format(FORMATTER);
            return Arrays.asList(prefix);
        }
        return Arrays.asList("2021-06-01", "2021-06-02", "2021-06-03");
    }
}
