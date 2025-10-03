package helio;

import helio.ui.Ui;
import helio.storage.Storage;
import helio.task.TaskList;
import helio.command.Command;
import helio.parser.Parser;

public class Helio {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Helio(String dir, String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(dir, fileName);
        this.tasks = new TaskList();
        storage.load(tasks); // load tasks if file exists
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            if (fullCommand == null) {
                break;
            }
            if (fullCommand.trim().isEmpty()) {
                continue;
            }

            ui.showLine();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        new Helio("data", "helio.txt").run();
    }
}
