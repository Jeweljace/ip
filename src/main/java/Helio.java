import java.util.Scanner;

public class Helio {
    private static final String FILE_PATH_DIR = "data";
    private static final String FILE_NAME = "helio.txt";

    public static void main(String[] args) {
        Task[] userList = new Task[100];
        int count = 0;
        Storage storage = new Storage();
        count = storage.load(userList);

        String logo = "        ╱|\n" +
                "       (˚ˎ 。7\n" +
                "        |、˜〵\n" +
                "       じしˍ,)ノ\n";

        Message.greet(logo);
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
            Message.showEntered(input);
            if (input.equals("bye")) {
                Message.bye();
                break;
            } else if (input.equals("help")) {
                Message.help();
            } else if (input.equals("list")) {
                if (count == 0) {
                    Message.emptyList();
                } else {
                    Message.listHeader();
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + ". " + userList[i]);
                    }
                    Message.listFooter();
                }
            } else if (input.startsWith("mark ")) {
                String check = input.substring(5).trim();
                if (check.isEmpty()) {
                    Handle.missingTaskNumber();
                    continue;
                }
                int taskNum;
                try {
                    taskNum = Integer.parseInt(check.split("\\s+")[0]);
                } catch (NumberFormatException e) {
                    Handle.invalidTaskNumber();
                    continue;
                }
                if (taskNum <= 0 || taskNum > count) {
                    Handle.invalidTaskNumber();
                    continue;
                }
                userList[taskNum - 1].markAsDone();
                Message.markedDone(userList[taskNum - 1].toString());
                storage.save(userList, count);
            } else if (input.startsWith("unmark ")) {
                String check = input.substring(7).trim();
                if (check.isEmpty()) {
                    Handle.missingTaskNumber();
                    continue;
                }
                int taskNum;
                try {
                    taskNum = Integer.parseInt(check.split("\\s+")[0]);
                } catch (NumberFormatException e) {
                    Handle.invalidTaskNumber();
                    continue;
                }
                if (taskNum <= 0 || taskNum > count) {
                    Handle.invalidTaskNumber();
                    continue;
                }
                userList[taskNum - 1].markAsNotDone();
                Message.markedUndone(userList[taskNum - 1].toString());
                storage.save(userList, count);
            } else if (input.startsWith("todo ")) {
                String description = (input.substring(5));
                if (description.isEmpty()) {
                    Handle.emptyTodoDescription();
                    continue;
                }
                userList[count] = new Todo(description);
                count++;
                Message.addedTask(userList[count - 1].toString(), count);
                storage.save(userList, count);
            } else if (input.startsWith("deadline ")) {
                String[] sections = input.substring(9).split(" /by ");
                if (sections[0].isEmpty() || sections[1].isEmpty()) {
                    Handle.emptyDeadlineParts();
                    continue;
                }
                userList[count] = new Deadline(sections[0], sections[1]);
                count++;
                Message.addedTask(userList[count - 1].toString(), count);
                storage.save(userList, count);
            } else if (input.startsWith("event ")) {
                String[] sections = input.substring(6).split(" /from | /to ");
                if (sections[0].isEmpty() || sections[1].isEmpty() || sections[2].isEmpty()) {
                    Handle.emptyEventParts();
                    continue;
                }
                String description = sections[0];
                String from = sections[1];
                String to = sections[2];
                userList[count] = new Event(description, from, to);
                count++;
                Message.addedTask(userList[count - 1].toString(), count);
                storage.save(userList, count);
            } else {
                Handle.invalidCommand();
            }
        }
    }
}
