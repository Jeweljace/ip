package helio.command;

import helio.ui.Ui;
import helio.storage.Storage;
import helio.task.TaskList;

/**
 * Displays a short usage guide listing all supported commands.
 * Usage: {@code help}
 */
public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
