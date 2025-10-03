package helio.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    // For Storage
    public ArrayList<Task> getAll() {
        return tasks;
    }

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
