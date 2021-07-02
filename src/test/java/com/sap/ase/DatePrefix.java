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
        while (prefixRange.isNotEmpty()) {
            result.addAll(prefixRange.prefixes);
            prefixRange = nextRange(prefixRange.nextFrom(), to);
        }
        return result;
    }

    private static PrefixRange nextRange(LocalDate from, LocalDate to) {
        if (!isRangeStart(from)) {
            return nextDaysRange(from, to);
        }
        PrefixRange prefixRange = nextPrefixRange(from, to);
        if (prefixRange.isNotEmpty()) {
            return prefixRange;
        }
        return nextDaysRange(from, to);
    }

    public static PrefixRange nextDaysRange(LocalDate from, LocalDate to) {
        LocalDate rangeEndDay = getNextRangeEndDay(from, to);
        return PrefixRange.daysRange(from, rangeEndDay);
    }

    private static PrefixRange nextPrefixRange(LocalDate from, LocalDate to) {
        LocalDate nextTo = to;
        while (nextTo.isAfter(from)) {
            Optional<String> prefixIfAny = getPrefixIfAny(from, nextTo);
            if (prefixIfAny.isPresent()) {
                return PrefixRange.prefixRange(from, nextTo, prefixIfAny.get());
            }
            nextTo = oneDayBefore(nextTo);
        }
        return PrefixRange.emptyRange(from);
    }

    private static Optional<String> getPrefixIfAny(LocalDate from, LocalDate to) {
        for (int trimLength = 1; trimLength <= 5; trimLength++) {
            if (fulfilledPrefix(from, to, trimLength)) {
                return Optional.of(toPrefix(from, trimLength));
            }
        }
        return Optional.empty();
    }

    private static boolean fulfilledPrefix(LocalDate from, LocalDate to, int trimLength) {
        String prefix = toPrefix(from, trimLength);
        return toPrefix(to, trimLength).equals(prefix)
                && !toPrefix(oneDayBefore(from), trimLength).equals(prefix)
                && !toPrefix(oneDayAfter(to), trimLength).equals(prefix);
    }

    private static boolean isRangeStart(LocalDate day) {
        return !toPrefix(day, 1).equals(toPrefix(oneDayBefore(day), 1));
    }

    private static boolean isRangeEnd(LocalDate day) {
        return !toPrefix(day, 1).equals(toPrefix(oneDayAfter(day), 1));
    }

    private static LocalDate getNextRangeEndDay(LocalDate from, LocalDate to) {
        for (LocalDate day = oneDayAfter(from); !day.isAfter(to); day = oneDayAfter(day)) {
            if (isRangeEnd(day)) {
                return day;
            }
        }
        return to;
    }

    private static LocalDate oneDayBefore(LocalDate day) {
        return day.minusDays(1);
    }

    private static LocalDate oneDayAfter(LocalDate day) {
        return day.plusDays(1);
    }

    private static String toPrefix(LocalDate date, int trimLength) {
        String string = toString(date);
        return string.substring(0, string.length() - trimLength);
    }

    private static String toString(LocalDate from) {
        return from.format(FORMATTER);
    }

    static class PrefixRange {
        final List<String> prefixes;
        final LocalDate from;
        final LocalDate to;

        public PrefixRange(LocalDate from, LocalDate to, List<String> prefixes) {
            this.from = from;
            this.to = to;
            this.prefixes = prefixes;
        }

        static PrefixRange daysRange(LocalDate from, LocalDate to) {
            return new PrefixRange(from, to, listDays(from, to));
        }

        private static PrefixRange prefixRange(LocalDate from, LocalDate nextTo, String prefix) {
            return new PrefixRange(from, nextTo, Collections.singletonList(prefix));
        }

        private static PrefixRange emptyRange(LocalDate from) {
            return new PrefixRange(from, oneDayBefore(from), Collections.emptyList());
        }

        private static List<String> listDays(LocalDate from, LocalDate to) {
            return Stream.iterate(from, date -> oneDayAfter(date))
                    .limit(daysBetween(from, to))
                    .map(DatePrefix::toString)
                    .collect(Collectors.toList());
        }

        private static long daysBetween(LocalDate from, LocalDate to) {
            return ChronoUnit.DAYS.between(from, to) + 1;
        }

        private boolean isNotEmpty() {
            return !from.isAfter(to);
        }

        private LocalDate nextFrom() {
            return oneDayAfter(to);
        }

    }
}
