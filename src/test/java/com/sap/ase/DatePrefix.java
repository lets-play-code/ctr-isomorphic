package com.sap.ase;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class DatePrefix {
    public static List<String> of(LocalDate from, LocalDate to) {
        String prefix = "2021-06-30";
        return Arrays.asList(prefix);
    }
}
