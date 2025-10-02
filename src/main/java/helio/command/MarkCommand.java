package helio.command;

import helio.ui.Ui;
import helio.storage.Storage;
import helio.task.Task;
import helio.task.TaskList;

public class MarkCommand extends Command {
    private final String args;

    public MarkCommand(String args) {
        this.args = args == null ? "" : args.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (args.isEmpty()) {
            ui.showError("Missing task number.");
            return;
        }
        int idx;
        try {
            idx = Integer.parseInt(args.split("\\s+")[0]) - 1;
        } catch (NumberFormatException e) {
            ui.showError("Invalid task number.");
            return;
        }
        if (idx < 0 || idx >= tasks.size()) {
            ui.showError("Task number out of range.");
            return;
        }

        Task t = tasks.getTask(idx);
        t.markAsDone();
        ui.showMarked(t);
        storage.save(tasks);
    }
}
