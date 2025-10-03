package helio.task;

import java.time.LocalDateTime;

import helio.time.DateTimeUtil;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final boolean hasTimeFrom;
    private final boolean hasTimeTo;

    // From user input (raw strings)
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

    // From storage (already-parsed values + explicit flags).
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

    public LocalDateTime getFrom() {
        return from;
    }

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
