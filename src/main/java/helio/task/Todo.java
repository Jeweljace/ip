package helio.task;

/**
 * Represents a simple task without any date or time attached.
 * Usage example:
 * todo read book
 */
public class Todo extends Task {
    /**
     * Creates a todo task with the given description.
     *
     * @param description the task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation prefixed with {@code [T]}.
     *
     * @return the formatted todo string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
