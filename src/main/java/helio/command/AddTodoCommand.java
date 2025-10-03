package helio.command;

import helio.ui.Ui;
import helio.storage.Storage;
import helio.task.TaskList;
import helio.task.Todo;

/**
 * Adds a {@code Todo} task to the task list.
 * Usage: {@code todo <description>}
 */
public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String args) {
        this.description = args == null ? "" : args.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (description.isEmpty()) {
            ui.showError("The description of a todo cannot be empty.");
            return;
        }
        if (description.contains("|")) {
            ui.showError("Description cannot contain '|'");
            return;
        }
        Todo t = new Todo(description);
        tasks.addTask(t);
        ui.showTaskAdded(t, tasks.size());
        if (!storage.save(tasks)) {
            ui.showError("Could not save tasks to disk.");
        }
    }
}
