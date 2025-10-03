package helio.task;

import java.util.ArrayList;

/**
 * Encapsulates a mutable list of {@link Task}s and provides common operations
 * such as add, delete, access, and keyword search.
 * Acts as the single source of truth for tasks while the program is running.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the end of the list.
     *
     * @param t the task to add
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Removes and returns the task at the given 0-based index.
     *
     * @param index the 0-based position to remove
     * @return the removed task
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the task at the given 0-based index.
     *
     * @param index the 0-based position to retrieve
     * @return the task at the position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return the size of the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns whether the list contains no tasks.
     *
     * @return {@code true} if the list is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns a live view of all tasks (backed by the internal list).
     *
     * @return the underlying list of tasks
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Returns a new {@code TaskList} containing tasks whose description contains
     * the given keyword (case-insensitive).
     *
     * @param keyword the substring to search for in task descriptions
     * @return a {@code TaskList} with only the matching tasks
     */
    public TaskList findContaining(String keyword) {
        String k = keyword.toLowerCase();
        TaskList result = new TaskList();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(k)) {
                result.addTask(t);
            }
        }
        return result;
    }
}
