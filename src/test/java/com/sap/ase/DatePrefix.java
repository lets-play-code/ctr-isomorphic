package com.sap.ase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatePrefix {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    static class PrefixRange {
        List<String> prefixes = new ArrayList<>();
        final LocalDate from;
        final LocalDate to;

        public PrefixRange(LocalDate from, LocalDate to) {
            this.from = from;
            this.to = to;
        }

        private LocalDate nextFrom() {
            return to.plusDays(1);
        }

        private PrefixRange nextSinglePrefixRange(LocalDate from) {
            LocalDate nextTo = this.to;
            while (!nextTo.isBefore(from)) {
                Optional<String> prefixIfAny = getPrefixIfAny(from, nextTo);
                if (prefixIfAny.isPresent()) {
                    PrefixRange prefixRange = new PrefixRange(from, nextTo);
                    prefixRange.prefixes.add(prefixIfAny.get());
                    return prefixRange;
                }
                nextTo = nextTo.minusDays(1);
            }
            return new PrefixRange(from, nextTo);
        }
    }

    public static List<String> of(LocalDate from, LocalDate to) {
        if (from.equals(to)) {
            return Arrays.asList(toString(from));
        }

        List<String> result = new ArrayList<>();
        PrefixRange range = new PrefixRange(from, to);
        PrefixRange prefixRange = range.nextSinglePrefixRange(from);
        result.addAll(prefixRange.prefixes);
        result.addAll(listDays(prefixRange.nextFrom(), to));

        return result;
    }

    private static List<String> listDays(LocalDate from, LocalDate to) {
        return Stream.iterate(from, date -> date.plusDays(1))
                .limit(daysBetween(from, to))
                .map(DatePrefix::toString)
                .collect(Collectors.toList());
    }

    private static Optional<String> getPrefixIfAny(LocalDate from, LocalDate to) {
        for (int trimLength = 1; trimLength <= 5; trimLength++) {
            if (fulfilledPrefix(from, to, trimLength)) {
                return Optional.of(toPrefix(from, trimLength));
            }
        }
        return Optional.empty();
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
