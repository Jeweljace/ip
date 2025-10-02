package helio.command;

import helio.ui.Ui;
import helio.storage.Storage;
import helio.task.TaskList;
import helio.task.Deadline;

public class AddDeadlineCommand extends Command {
    private final String args;

    public AddDeadlineCommand(String args) {
        this.args = args == null ? "" : args.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] p = args.split("\\s*/by\\s*", 2);
        if (p.length < 2 || p[0].isEmpty() || p[1].isEmpty()) {
            ui.showError("Deadline must be: deadline <desc> /by <time>");
            return;
        }
        Deadline t = new Deadline(p[0], p[1]);
        tasks.addTask(t);
        ui.showTaskAdded(t, tasks.size());
        storage.save(tasks);
    }
}
