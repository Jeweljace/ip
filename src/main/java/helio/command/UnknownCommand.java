package helio.command;

import helio.ui.Ui;
import helio.storage.Storage;
import helio.task.TaskList;

public class UnknownCommand extends Command {
    private final String name;

    public UnknownCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("Unknown command: " + name + ". Type 'help' to see valid commands.");
    }
}