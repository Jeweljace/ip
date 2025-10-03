package helio.task;

import java.time.LocalDateTime;

import helio.time.DateTimeUtil;

/**
 * Represents a task that has a deadline, which may include an optional time component.
 * Example usages:
 * - deadline return book /by 2025-10-03
 * - deadline submit report /by 2025-10-03 1800
 */
public class Deadline extends Task {
    private final LocalDateTime by;
    private final boolean hasTime;

    /**
     * Constructs a deadline from raw user input.
     * Accepted formats:
     * - {@code yyyy-MM-dd}
     * - {@code yyyy-MM-dd HHmm}
     *
     * @param description the task description
     * @param byRaw       the raw date/time string entered by the user
     * @throws IllegalArgumentException if {@code byRaw} cannot be parsed
     */
    public Deadline(String description, String byRaw) {
        super(description);
        this.by = DateTimeUtil.parseDateTimeFlexible(byRaw);
        this.hasTime = byRaw.trim().contains(" "); // "yyyy-MM-dd HHmm"
    }

    /**
     * Constructs a deadline from parsed values (used by {@code Storage}).
     *
     * @param description the task description
     * @param by          the parsed due date/time
     * @param hasTime     whether the original input included a time component
     */
    public Deadline(String description, LocalDateTime by, boolean hasTime) {
        super(description);
        this.by = by;
        this.hasTime = hasTime;
    }

    /**
     * Returns the due date/time.
     *
     * @return the {@code LocalDateTime} representing the deadline
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns whether the input included a time component.
     *
     * @return {@code true} if a time was provided; {@code false} otherwise
     */
    public boolean hasTime() {
        return hasTime;
    }


    @Override
    public String toString() {
        String formatted = hasTime
                ? DateTimeUtil.formatDateTime(by)   // show date + time
                : DateTimeUtil.formatDate(by.toLocalDate()); // show just date
        return "[D]" + super.toString() + " (by: " + formatted + ")";
    }

    /**
     * Save using the same format accepted for input, plus a hasTime flag (1/0)
     */
    @Override
    public String toSave() {
        String byField = hasTime
                ? DateTimeUtil.formatDateTimeForFile(by)
                : DateTimeUtil.formatDateForFile(by.toLocalDate());
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription()
                + " | " + byField
                + " | " + (hasTime ? "1" : "0");
    }
}
