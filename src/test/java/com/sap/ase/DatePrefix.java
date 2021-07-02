package com.sap.ase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatePrefix {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<String> of(LocalDate from, LocalDate to) {
        List<String> result = new ArrayList<>();
        PrefixRange prefixRange = nextRange(from, to);
        while (!prefixRange.isEmpty()) {
            result.addAll(prefixRange.prefixes);
            prefixRange = nextRange(prefixRange.nextFrom(), to);
        }
        return result;
    }

    private static PrefixRange nextRange(LocalDate from, LocalDate to) {
        if (isNotRangeStart(from)) {
            return nextDaysRange(from, to);
        }
        PrefixRange prefixRange = nextSinglePrefixRange(from, to);
        if (!prefixRange.isEmpty()) {
            return prefixRange;
        }
        return nextDaysRange(from, to);
    }

    private static PrefixRange nextSinglePrefixRange(LocalDate from, LocalDate to) {
        LocalDate nextTo = to;
        while (nextTo.isAfter(from)) {
            Optional<String> prefixIfAny = getPrefixIfAny(from, nextTo);
            if (prefixIfAny.isPresent()) {
                PrefixRange prefixRange = new PrefixRange(from, nextTo);
                prefixRange.prefixes.add(prefixIfAny.get());
                return prefixRange;
            }
            nextTo = nextTo.minusDays(1);
        }
        return new PrefixRange(from, from.minusDays(1));
    }

    public static PrefixRange nextDaysRange(LocalDate from, LocalDate to) {
        LocalDate nextRangeStartDay = getNextRangeEndDay(from, to);
        PrefixRange prefixRange = new PrefixRange(from, nextRangeStartDay);
        prefixRange.prefixes.addAll(listDays(from, nextRangeStartDay));
        return prefixRange;
    }

    static class PrefixRange {
        List<String> prefixes = new ArrayList<>();
        final LocalDate from;
        final LocalDate to;

        public PrefixRange(LocalDate from, LocalDate to) {
            this.from = from;
            this.to = to;
        }

        private boolean isEmpty() {
            return to.isBefore(from);
        }

        private LocalDate nextFrom() {
            return to.plusDays(1);
        }

    }

    private static boolean isNotRangeStart(LocalDate day) {
        return !isRangeStart(day);
    }

    private static boolean isRangeStart(LocalDate day) {
        return !toPrefix(day, 1).equals(toPrefix(day.minusDays(1), 1));
    }

    private static LocalDate getNextRangeEndDay(LocalDate from, LocalDate to) {
        for (LocalDate day = from.plusDays(1); !day.isAfter(to); day = day.plusDays(1)) {
            if (isRangeEnd(day)) {
                return day;
            }
        }
        return to;
    }

    private static boolean isRangeEnd(LocalDate day) {
        return !toPrefix(day, 1).equals(toPrefix(day.plusDays(1), 1));
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

    private static String toPrefix(LocalDate date, int trimLength) {
        String string = toString(date);
        return string.substring(0, string.length() - trimLength);
    }

    private static boolean fulfilledPrefix(LocalDate from, LocalDate to, int trimLength) {
        String prefix = toPrefix(from, trimLength);
        return toPrefix(to, trimLength).equals(prefix)
                && !toPrefix(from.minusDays(1), trimLength).equals(prefix)
                && !toPrefix(to.plusDays(1), trimLength).equals(prefix);
    }

    private static long daysBetween(LocalDate from, LocalDate to) {
        return ChronoUnit.DAYS.between(from, to) + 1;
    }

    private static String toString(LocalDate from) {
        return from.format(FORMATTER);
    }
}
