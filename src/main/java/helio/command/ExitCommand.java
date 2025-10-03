package helio.command;

import helio.ui.Ui;
import helio.storage.Storage;
import helio.task.TaskList;

/**
 * Exits the application.
 * Usage: {@code bye}
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}