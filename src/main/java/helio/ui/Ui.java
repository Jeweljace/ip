package helio.ui;

import java.util.Scanner;
import helio.task.Task;
import helio.task.TaskList;

public class Ui {
    private final Scanner in = new Scanner(System.in);
    private static final String LINE = "____________________________________________________________";

    public String readCommand() {
        return in.hasNextLine() ? in.nextLine() : null;
    }

    public static void showWelcome(String logo) {
        System.out.println(LINE);
        System.out.println(" Hello... I'm Helio o.o");
        System.out.println(logo);
        System.out.println(" What can I do for you? ._.");
        System.out.println(LINE);
    }

    public static void showBye() {
        System.out.println(LINE);
        System.out.println(" Bye bye! Time for my nap!! ");
        System.out.println(LINE);
    }

    public static void showHelp() {
        System.out.println(" List of valid inputs:");
        System.out.println("  - list");
        System.out.println("  - mark <task number>");
        System.out.println("  - unmark <task number>");
        System.out.println("  - todo <description>");
        System.out.println("  - deadline <description> /by <deadline>");
        System.out.println("  - event <description> /from <start> /to <end>");
        System.out.println("  - delete <task number>");
        System.out.println("  - help");
        System.out.println("  - bye");
    }

    public static void showLine() {
        System.out.println(LINE);
    }

    public static void showError(String message) {
        System.out.println("Error: " + message);
    }

    public static void showEntered(String input) {
        System.out.println(" Command entered: " + input);
    }

    public static void showTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println(" Nothing for meow ^.^");
            return;
        }
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
    }

    public static void showTaskAdded(Task t, int count) {
        System.out.println(" Got it!! I've added this task:");
        System.out.println("   " + t);
        System.out.println(" Now you have " + count + " tasks in your list.");
    }

    public static void showTaskRemoved(Task t, int count) {
        System.out.println(" Meow!! I've removed this task:");
        System.out.println("   " + t);
        System.out.println(" Now you have " + count + " tasks in the list:)");
    }

    public static void showMarked(Task t) {
        System.out.println(" Yay! I've meowed this task as done:");
        System.out.println(" " + t);
    }

    public static void showUnmarked(Task t) {
        System.out.println(" Aww okay... I've meowed this task as not done yet:");
        System.out.println(" " + t);
    }
}
