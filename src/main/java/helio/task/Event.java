package helio.task;

import java.time.LocalDateTime;

import helio.time.DateTimeUtil;

/**
 * Represents an event with a start and end time, where each endpoint may optionally
 * include a time component.
 * Example usages:
 * - event project meeting /from 2025-10-03 /to 2025-10-03
 * - event camp /from 2025-12-24 0900 /to 2025-12-26 1700
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final boolean hasTimeFrom;
    private final boolean hasTimeTo;

    /**
     * Constructs an event from raw user input.
     * Accepted formats for {@code from} and {@code to}:
     * - {@code yyyy-MM-dd}
     * - {@code yyyy-MM-dd HHmm}
     *
     * @param description the task description
     * @param fromRaw     the raw start date/time string
     * @param toRaw       the raw end date/time string
     * @throws IllegalArgumentException if parsing fails or if {@code to} is before {@code from}
     */
    public Event(String description, String fromRaw, String toRaw) {
        super(description);
        this.from = DateTimeUtil.parseDateTimeFlexible(fromRaw);
        this.to = DateTimeUtil.parseDateTimeFlexible(toRaw);
        if (this.to.isBefore(this.from)) {
            throw new IllegalArgumentException("Event end time cannot be before start time.");
        }
        this.hasTimeFrom = fromRaw.trim().contains(" ");
        this.hasTimeTo = toRaw.trim().contains(" ");
    }

    /**
     * Constructs an event from parsed values (used by {@code Storage}).
     *
     * @param description the task description
     * @param from        the parsed start date/time
     * @param to          the parsed end date/time
     * @param hasTimeFrom whether the original input for {@code from} included a time
     * @param hasTimeTo   whether the original input for {@code to} included a time
     */
    public Event(String description, LocalDateTime from, LocalDateTime to,
                 boolean hasTimeFrom, boolean hasTimeTo) {
        super(description);
        if (to.isBefore(from)) {
            throw new IllegalArgumentException("Event end time cannot be before start time.");
        }
        this.from = from;
        this.to = to;
        this.hasTimeFrom = hasTimeFrom;
        this.hasTimeTo = hasTimeTo;
    }

    /**
     * Returns the start date/time of the event.
     *
     * @return the start {@code LocalDateTime}
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end date/time of the event.
     *
     * @return the end {@code LocalDateTime}
     */
    public LocalDateTime getTo() {
        return to;
    }

    public boolean hasTimeFrom() {
        return hasTimeFrom;
    }

    public boolean hasTimeTo() {
        return hasTimeTo;
    }

    @Override
    public String toString() {
        String left = hasTimeFrom ? DateTimeUtil.formatDateTime(from)
                : DateTimeUtil.formatDate(from.toLocalDate());
        String right = hasTimeTo ? DateTimeUtil.formatDateTime(to)
                : DateTimeUtil.formatDate(to.toLocalDate());
        return "[E]" + super.toString() + " (from: " + left + " to: " + right + ")";
    }

    @Override
    public String toSave() {
        // Save using the same formats, plus flags so can display the same way on load
        String fromField = hasTimeFrom
                ? DateTimeUtil.formatDateTimeForFile(from)
                : DateTimeUtil.formatDateForFile(from.toLocalDate());
        String toField = hasTimeTo
                ? DateTimeUtil.formatDateTimeForFile(to)
                : DateTimeUtil.formatDateForFile(to.toLocalDate());
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription()
                + " | " + fromField
                + " | " + toField
                + " | " + (hasTimeFrom ? "1" : "0")
                + " | " + (hasTimeTo ? "1" : "0");
    }
}
