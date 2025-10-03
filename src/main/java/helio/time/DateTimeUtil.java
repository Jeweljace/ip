package helio.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility methods for parsing and formatting dates and times used in tasks.
 * Supported user input:
 * - Date only: {@code yyyy-MM-dd} (e.g., 2025-10-03)
 * - Date + time: {@code yyyy-MM-dd HHmm} (e.g., 2025-10-03 1800)
 * Pretty output formats:
 * - Date only: {@code MMM d yyyy} (e.g., Oct 3 2025)
 * - Date + time: {@code MMM d yyyy HH:mm} (e.g., Oct 3 2025 18:00)
 * File persistence formats:
 * - Date only: {@code yyyy-MM-dd}
 * - Date + time: {@code yyyy-MM-dd HHmm}
 */
public final class DateTimeUtil {
    private DateTimeUtil() {
    }

    private static final DateTimeFormatter PRETTY_DATE = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter PRETTY_DATETIME = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    private static final DateTimeFormatter FILE_DATE = DateTimeFormatter.ISO_LOCAL_DATE;            // yyyy-MM-dd
    private static final DateTimeFormatter FILE_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses a date string in {@code yyyy-MM-dd} format.
     *
     * @param s the raw input string
     * @return the parsed {@code LocalDate}
     * @throws IllegalArgumentException if the input cannot be parsed
     */
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

    /**
     * Parses a date/time string in either of the supported formats:
     * - {@code yyyy-MM-dd HHmm} (e.g., 2025-10-03 1800)
     * - {@code yyyy-MM-dd} (interpreted as midnight)
     *
     * @param s the raw input string
     * @return the parsed {@code LocalDateTime}
     * @throws IllegalArgumentException if the input cannot be parsed
     */
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

    /**
     * Formats a {@code LocalDate} for user-facing display.
     *
     * @param d the date to format
     * @return a string in {@code MMM d yyyy} format (e.g., Oct 3 2025)
     */
    public static String formatDate(LocalDate d) {
        return d.format(PRETTY_DATE);
    }

    /**
     * Formats a {@code LocalDateTime} for user-facing display.
     *
     * @param dt the date/time to format
     * @return a string in {@code MMM d yyyy HH:mm} format (e.g., Oct 3 2025 18:00)
     */
    public static String formatDateTime(LocalDateTime dt) {
        return dt.format(PRETTY_DATETIME);
    }

    /**
     * Formats a {@code LocalDate} for persistence in save files.
     *
     * @param d the date to format
     * @return a string in {@code yyyy-MM-dd} format
     */
    public static String formatDateForFile(LocalDate d) {
        return d.format(FILE_DATE);
    }

    /**
     * Formats a {@code LocalDateTime} for persistence in save files.
     *
     * @param dt the date/time to format
     * @return a string in {@code yyyy-MM-dd HHmm} format
     */
    public static String formatDateTimeForFile(LocalDateTime dt) {
        return dt.format(FILE_DATETIME);
    }
}
