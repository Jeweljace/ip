package helio.command;

import helio.ui.Ui;
import helio.storage.Storage;
import helio.task.TaskList;
import helio.task.Event;

public class AddEventCommand extends Command {
    private final String args;

    public AddEventCommand(String args) {
        this.args = args == null ? "" : args.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Split once by /from, then by /to
        String[] first = args.split("\\s*/from\\s*", 2);
        if (first.length < 2 || first[0].isEmpty()) {
            ui.showError("Event must be: event <desc> /from <start> /to <end>");
            return;
        }
        String[] second = first[1].split("\\s*/to\\s*", 2);
        if (second.length < 2 || second[0].isEmpty() || second[1].isEmpty()) {
            ui.showError("Event must be: event <desc> /from <start> /to <end>");
            return;
        }

        Event t = new Event(first[0], second[0], second[1]);
        tasks.addTask(t);
        ui.showTaskAdded(t, tasks.size());
        storage.save(tasks);
    }
}
