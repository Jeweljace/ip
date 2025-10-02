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

    public void showWelcome() {
        System.out.println(LINE);
        System.out.println(" Hello... I'm Helio o.o");
        System.out.println("        ╱|\n" +
                "       (˚ˎ 。7\n" +
                "        |、˜〵\n" +
                "       じしˍ,)ノ\n");
        System.out.println(" What can I do for you? ._.");
        System.out.println(" psssst if you don't know what I can do just enter the magic word --> help");
        System.out.println(LINE);
    }

    public void showBye() {
        System.out.println(LINE);
        System.out.println(" Bye bye! Time for my nap!! ");
        System.out.println(LINE);
    }

    public void showHelp() {
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

    public void showLine() {
        System.out.println(LINE);
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showEntered(String input) {
        System.out.println(" Command entered: " + input);
    }

    public void showTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println(" Nothing for meow ^.^");
            return;
        }
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
    }

    public void showTaskAdded(Task t, int count) {
        System.out.println(" Got it!! I've added this task:");
        System.out.println("   " + t);
        System.out.println(" Now you have " + count + " tasks in your list.");
    }

    public void showTaskRemoved(Task t, int count) {
        System.out.println(" Meow!! I've removed this task:");
        System.out.println("   " + t);
        System.out.println(" Now you have " + count + " tasks in the list:)");
    }

    public void showMarked(Task t) {
        System.out.println(" Yay! I've meowed this task as done:");
        System.out.println(" " + t);
    }

    public void showUnmarked(Task t) {
        System.out.println(" Aww okay... I've meowed this task as not done yet:");
        System.out.println(" " + t);
    }
}
