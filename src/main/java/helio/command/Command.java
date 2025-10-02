package helio.command;

import helio.ui.Ui;
import helio.storage.Storage;
import helio.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
