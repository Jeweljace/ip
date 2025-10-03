package helio.command;

import helio.ui.Ui;
import helio.storage.Storage;
import helio.task.TaskList;

/**
 * Represents a user command that can be executed against the current state
 * (task list, UI, and storage).
 * Concrete subclasses implement specific actions such as adding, listing,
 * or deleting tasks.
 */
public abstract class Command {
    /**
     * Executes this command.
     *
     * @param tasks   the current task list
     * @param ui      the UI for user interaction
     * @param storage the storage layer for persistence
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns {@code true} if this command should terminate the app after execution.
     *
     * @return whether the app should exit
     */
    public boolean isExit() {
        return false;
    }
}
