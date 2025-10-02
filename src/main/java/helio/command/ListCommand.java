package helio.command;

import helio.ui.Ui;
import helio.storage.Storage;
import helio.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }
}
