package helio;

import helio.ui.Ui;
import helio.storage.Storage;
import helio.task.TaskList;
import helio.command.Command;
import helio.parser.Parser;

/**
 * Entry point of the Helio task manager application.
 * Helio is a simple CLI-based task manager that supports commands such as:
 * adding tasks (todo, deadline, event), listing tasks, marking/unmarking,
 * deleting, searching, and saving/loading tasks from disk.
 */
public class Helio {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a {@code Helio} app that uses the given save file.
     *
     * @param fileDir  directory where the save file is stored
     * @param fileName name of the save file
     */
    @SuppressWarnings("JavadocReference")
    public Helio(String dir, String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(dir, fileName);
        this.tasks = new TaskList();
        storage.load(tasks); // load tasks if file exists
    }

    /**
     * Runs the main command loop of the application.
     * The loop repeatedly:
     * Reads user input from the UI.
     * Parses the input into a {@code Command}.
     * Executes the command, updating tasks and UI as needed.
     * Saves the task list to disk when modified.
     * Terminates when the user enters the {@code bye} command.
     */
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

    /**
     * Main entry point. Starts the Helio app with the default save file location.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new Helio("data", "helio.txt").run();
    }
}
