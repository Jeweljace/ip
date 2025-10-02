package helio;

import java.util.Scanner;
import java.util.ArrayList;

import helio.storage.Storage;
import helio.task.Task;
import helio.task.Todo;
import helio.task.Deadline;
import helio.task.Event;
import helio.ui.Ui;

public class Helio {
    private static final String FILE_PATH_DIR = "data";
    private static final String FILE_NAME = "helio.txt";

    public static void main(String[] args) {
        ArrayList<Task> userList = new ArrayList<>();
        Storage storage = new Storage(FILE_PATH_DIR, FILE_NAME);  // NEW
        storage.load(userList);

        String logo = "        ╱|\n" +
                "       (˚ˎ 。7\n" +
                "        |、˜〵\n" +
                "       じしˍ,)ノ\n";

        Ui.showWelcome(logo);
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String input = in.nextLine();
            if (input == null) {
                break;
            }
            input = input.trim();
            if (input.isEmpty()) {
                continue;
            }
            Ui.showEntered(input);
            if (input.equals("bye")) {
                Ui.showBye();
                break;
            } else if (input.equals("help")) {
                Ui.showHelp();
            } else if (input.equals("list")) {
                Ui.showTasks();
            } else if (input.startsWith("mark ")) {
                String check = input.substring(5).trim();
                if (check.isEmpty()) {
                    Ui.missingTaskNumber();
                    continue;
                }
                int taskNum;
                try {
                    taskNum = Integer.parseInt(check.split("\\s+")[0]);
                } catch (NumberFormatException e) {
                    Ui.invalidTaskNumber();
                    continue;
                }
                if (taskNum <= 0 || taskNum > userList.size()) {
                    Ui.invalidTaskNumber();
                    continue;
                }
                Task t = userList.get(taskNum - 1);
                t.markAsDone();
                Ui.showMarked(t);
                storage.save(userList);
            } else if (input.startsWith("unmark ")) {
                String check = input.substring(7).trim();
                if (check.isEmpty()) {
                    Ui.missingTaskNumber();
                    continue;
                }
                int taskNum;
                try {
                    taskNum = Integer.parseInt(check.split("\\s+")[0]);
                } catch (NumberFormatException e) {
                    Ui.invalidTaskNumber();
                    continue;
                }
                if (taskNum <= 0 || taskNum > userList.size()) {
                    Ui.invalidTaskNumber();
                    continue;
                }
                Task t = userList.get(taskNum - 1);
                t.markAsNotDone();
                Ui.showUnmarked(t.toString());
                storage.save(userList);
            } else if (input.startsWith("todo ")) {
                String description = (input.substring(5));
                if (description.isEmpty()) {
                    Ui.emptyTodoDescription();
                    continue;
                }
                Task t = new Todo(description);
                userList.add(t);
                Ui.showTaskAdded(t.toString(), userList.size());
                storage.save(userList);
            } else if (input.startsWith("deadline ")) {
                String[] sections = input.substring(9).split(" /by ", 2);
                if (sections.length < 2 || sections[0].isEmpty() || sections[1].isEmpty()) {
                    Ui.emptyDeadlineParts();
                    continue;
                }
                Task t = new Deadline(sections[0], sections[1]);
                userList.add(t);
                Ui.showTaskAdded(t.toString(), userList.size());
                storage.save(userList);
            } else if (input.startsWith("event ")) {
                String[] sections = input.substring(6).split(" /from | /to ", 3);
                if (sections.length < 3 || sections[0].isEmpty() || sections[1].isEmpty() || sections[2].isEmpty()) {
                    Ui.emptyEventParts();
                    continue;
                }
                String description = sections[0];
                String from = sections[1];
                String to = sections[2];
                Task t = new Event(description, from, to);
                userList.add(t);
                Ui.showTaskAdded(t.toString(), userList.size());
                storage.save(userList);
            } else if (input.startsWith("delete ")) {
                String check = input.substring(7).trim();
                if (check.isEmpty()) {
                    Ui.missingTaskNumber();
                    continue;
                }
                int taskNum;
                try {
                    taskNum = Integer.parseInt(check.split("\\s+")[0]);
                } catch (NumberFormatException e) {
                    Ui.invalidTaskNumber();
                    continue;
                }
                if (taskNum <= 0 || taskNum > userList.size()) {
                    Ui.invalidTaskNumber();
                    continue;
                }
                Task t = userList.get(taskNum - 1);
                userList.remove(taskNum - 1);
                Ui.showTaskRemoved(t, userList.size());
                storage.save(userList);
            } else {
                Ui.invalidCommand();
            }
        }
    }
}
