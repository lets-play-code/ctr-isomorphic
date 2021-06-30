package com.sap.ase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatePrefix {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<String> of(LocalDate from, LocalDate to) {
        if (from.equals(to)) {
            return Arrays.asList(toString(from));
        }

        for (LocalDate prefixEnd = to; prefixEnd.isAfter(from); prefixEnd = prefixEnd.minusDays(1)) {

        }

        List<String> trimLength1 = getPrefixIfAny(from, to);
        if (!trimLength1.isEmpty()) return trimLength1;
        List<String> trimLength = getPrefixIfAny(from, to.minusDays(1));
        if (!trimLength.isEmpty()) {
            List<String> temp = new ArrayList<>(trimLength);
            temp.add(toString(to));
            return temp;
        }

        return Stream.iterate(from, date -> date.plusDays(1))
                .limit(daysBetween(from, to))
                .map(DatePrefix::toString)
                .collect(Collectors.toList());
    }

    private static List<String> getPrefixIfAny(LocalDate from, LocalDate to) {
        for (int trimLength = 1; trimLength <= 5; trimLength++) {
            if (fulfilledPrefix(from, to, trimLength)) {
                return Arrays.asList(toPrefix(from, trimLength));
            }
        }
        return Collections.emptyList();
    }

    private static String toPrefix(LocalDate from, int trimLength) {
        String string = toString(from);
        return string.substring(0, string.length() - trimLength);
    }

    private static boolean fulfilledPrefix(LocalDate from, LocalDate to, int trimLength) {
        String prefix = toPrefix(from, trimLength);
        return toPrefix(to, trimLength).equals(prefix)
                && !toPrefix(from.minusDays(trimLength), trimLength).equals(prefix)
                && !toPrefix(to.plusDays(trimLength), trimLength).equals(prefix);
    }

    private static long daysBetween(LocalDate from, LocalDate to) {
        return ChronoUnit.DAYS.between(from, to) + 1;
    }

    private static String toString(LocalDate from) {
        return from.format(FORMATTER);
    }
}
