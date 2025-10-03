package helio.command;

import java.util.ArrayList;

import helio.ui.Ui;
import helio.storage.Storage;
import helio.task.Task;
import helio.task.TaskList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String args) {
        this.keyword = args == null ? "" : args.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (keyword.isEmpty()) {
            ui.showError("Usage: find <keyword>");
            return;
        }
        TaskList matches = tasks.findContaining(keyword);
        ui.showMatchingTasks(matches);
    }
}
