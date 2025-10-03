package helio.task;

import java.time.LocalDateTime;

import helio.time.DateTimeUtil;

public class Deadline extends Task {
    private final LocalDateTime by;
    private final boolean hasTime;

    // From user input
    public Deadline(String description, String byRaw) {
        super(description);
        this.by = DateTimeUtil.parseDateTimeFlexible(byRaw);
        this.hasTime = byRaw.trim().contains(" "); // "yyyy-MM-dd HHmm"
    }

    // From storage
    public Deadline(String description, LocalDateTime by, boolean hasTime) {
        super(description);
        this.by = by;
        this.hasTime = hasTime;
    }

    public LocalDateTime getBy() {
        return by;
    }

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

    @Override
    public String toSave() {
        // Save using the same format accepted for input, plus a hasTime flag (1/0)
        String byField = hasTime
                ? DateTimeUtil.formatDateTimeForFile(by)
                : DateTimeUtil.formatDateForFile(by.toLocalDate());
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription()
                + " | " + byField
                + " | " + (hasTime ? "1" : "0");
    }
}
