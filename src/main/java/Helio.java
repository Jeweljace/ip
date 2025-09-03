import java.util.Scanner;

public class Helio {
    public static void main(String[] args) {
        Task[] userList = new Task[100];
        int count = 0;
        String logo = "        ╱|\n" +
                "       (˚ˎ 。7\n" +
                "        |、˜〵\n" +
                "       じしˍ,)ノ\n";
        System.out.println("____________________________________________________________\n" +
                " Hello... I'm Helio o.o\n" +
                logo +
                " What can I do for you? ._. \n" +
                "____________________________________________________________");

        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________");
                break;
            } else if (input.equals("help")) {
                System.out.println("____________________________________________________________\n" +
                        " List of valid inputs:\n" +
                        " 1. list (view current list of tasks)\n" +
                        " 2. mark <task number> (mark a task as done)\n" +
                        " 3. unmark <task number> (mark a task as not done)\n" +
                        " 4. todo <task description> (add a task without any date/time attached to it to the list)\n" +
                        " 5. deadline <task description> /by <deadline> (add a task that need to be done before a specific date/time to the list)\n" +
                        " 6. event <task description> /from <date/time> /to <date/time> (add a task that starts at a specific date/time and ends at a specific date/time to the list)\n " +
                        " 7. help (shows list of valid commands)\n" +
                        " 8. bye (exits program)");

            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                if (count == 0) {
                    System.out.println(" Nothing for meow ^.^");
                } else {
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + ". " + userList[i]);
                    }
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("mark ")) {
                String check = (input.substring(5));
                if (check.isEmpty()) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Meow! Please enter a task number!! ");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                int taskNum = Integer.parseInt(input.split(" ")[1]);
                if  (taskNum <= 0 || taskNum > count) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Meow! Invalid task number!");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                userList[taskNum - 1].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println(" " + userList[taskNum - 1]);
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("unmark ")) {
                String check = (input.substring(7));
                if (check.isEmpty()) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Meow! Please enter a task number!! ");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                int taskNum = Integer.parseInt(input.split(" ")[1]);
                if  (taskNum <= 0 || taskNum > count) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Meow! Invalid task number!");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                userList[taskNum - 1].markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println(" " + userList[taskNum - 1]);
                System.out.println("______________________________________________________________");
            } else if (input.startsWith("todo ")) {
                String description = (input.substring(5));
                if (description.isEmpty()) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Meow! Description of todo cannot be empty! =.=");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                userList[count] = new Todo(description);
                count++;
                System.out.println("______________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + userList[count - 1]);
                System.out.println(" Now you have " + count + " tasks in the list.");
                System.out.println("______________________________________________________________");
            } else if (input.startsWith("deadline ")) {
                String[] sections = input.substring(9).split(" /by ");
                if (sections[0].isEmpty()|| sections[1].isEmpty()) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Meow! Please fill in a description and a date/time! =.=");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                userList[count] = new Deadline(sections[0], sections[1]);
                count++;
                System.out.println("______________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + userList[count - 1]);
                System.out.println(" Now you have " + count + " tasks in the list.");
                System.out.println("______________________________________________________________");
            } else if (input.startsWith("event ")) {
                String[] sections = input.substring(6).split(" /from | /to ");
                if (sections[0].isEmpty()|| sections[1].isEmpty()|| sections[2].isEmpty()) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Meow! Please fill in a description and date/time of start and end! =.=");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                String description = sections[0];
                String from = sections[1];
                String to = sections[2];
                userList[count] = new Event(description, from, to);
                count++;
                System.out.println("______________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + userList[count - 1]);
                System.out.println(" Now you have " + count + " tasks in the list.");
                System.out.println("______________________________________________________________");
            } else {
                System.out.println("____________________________________________________________\n" +
                        " Sorry, invalid input :< Please try again. See help for list of valid inputs!\n" +
                        "____________________________________________________________");
            }
        }
    }
}
