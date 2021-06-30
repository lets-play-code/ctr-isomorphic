package com.sap.ase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class DatePrefix {
    public static List<String> of(LocalDate from, LocalDate to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String prefix = from.format(formatter);
        return Arrays.asList(prefix);
    }
}
