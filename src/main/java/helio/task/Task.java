package helio.task;

/**
 * Represents a generic task with a textual description and a completion status.
 * Concrete subclasses (e.g., {@link Todo}, {@link Deadline}, {@link Event})
 * may add additional fields and override output formats.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a new task marked as not done.
     *
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a single-character status icon: {@code "X"} if done, space otherwise.
     *
     * @return the status icon string
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return {@code true} if done; {@code false} otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Serializes this task into a single-line save format understood by {@link helio.storage.Storage}.
     * Subclasses should override this method if they need to persist additional fields.
     *
     * @return a line suitable for writing to the save file
     */
    public String toSave() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a human-readable representation for displaying in the UI.
     *
     * @return the formatted task string
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
