package helio.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateTimeUtil {
    private DateTimeUtil() {
    }

    private static final DateTimeFormatter PRETTY_DATE = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter PRETTY_DATETIME = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    private static final DateTimeFormatter FILE_DATE = DateTimeFormatter.ISO_LOCAL_DATE;            // yyyy-MM-dd
    private static final DateTimeFormatter FILE_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    // Parses yyyy-MM-dd. Throws a friendly error if invalid.
    public static LocalDate parseDate(String s) {
        String trimmed = s.trim();
        try {
            return LocalDate.parse(trimmed, FILE_DATE);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid date: \"" + s + "\". Use yyyy-MM-dd (e.g., 2025-10-03)."
            );
        }
    }

    public static LocalDateTime parseDateTimeFlexible(String s) {
        String trimmed = s.trim();

        // "yyyy-MM-dd HHmm"
        try {
            return LocalDateTime.parse(trimmed, FILE_DATETIME);
        } catch (Exception ignore) {
        }

        // Date-only (yyyy-MM-dd)
        try {
            LocalDate d = LocalDate.parse(trimmed, FILE_DATE);
            return d.atStartOfDay();
        } catch (Exception ignore) {
        }

        // Friendly error
        throw new IllegalArgumentException(
                "Invalid date/time: \"" + s + "\". Use yyyy-MM-dd or yyyy-MM-dd HHmm (e.g., 2025-10-03 1800)."
        );
    }

    // Pretty-print for console
    public static String formatDate(LocalDate d) {
        return d.format(PRETTY_DATE);
    }

    public static String formatDateTime(LocalDateTime dt) {
        return dt.format(PRETTY_DATETIME);
    }

    // Formats for saving to file (must match what parseDate/parseDateTimeFlexible accept)
    public static String formatDateForFile(LocalDate d) {
        return d.format(FILE_DATE);
    }

    public static String formatDateTimeForFile(LocalDateTime dt) {
        return dt.format(FILE_DATETIME);
    }
}
