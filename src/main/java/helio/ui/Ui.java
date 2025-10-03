package helio.ui;

import java.util.Scanner;

import helio.task.Task;
import helio.task.TaskList;

/**
 * Handles all user interaction for the Helio CLI:
 * - reading user input
 * - printing normal messages, task lists, and errors
 * - displaying confirmations when tasks are added, removed, or updated
 */
public class Ui {
    private final Scanner in = new Scanner(System.in);
    private static final String LINE = "____________________________________________________________";

    /**
     * Reads one line of user input from standard input.
     *
     * @return the line read, or {@code null} if the input stream is closed
     */
    public String readCommand() {
        return in.hasNextLine() ? in.nextLine() : null;
    }

    /**
     * Prints the welcome banner and a short greeting.
     */
    public void showWelcome() {
        System.out.println(LINE);
        System.out.println(" Hello... I'm Helio o.o");
        System.out.println("  /\\_/\\\n"
                + " ( o.o )\n"
                + "  > ^ <\n");
        System.out.println(" What can I do for you? ._.");
        System.out.println(" psssst if you don't know what I can do just enter the magic word --> help");
        System.out.println(LINE);
    }

    /**
     * Prints the goodbye message when the program exits.
     */
    public void showBye() {
        System.out.println(LINE);
        System.out.println(" Bye bye! Time for my nap!! ");
        System.out.println(LINE);
    }

    /**
     * Prints the list of supported commands and their usage format.
     */
    public void showHelp() {
        System.out.println(" List of valid inputs:");
        System.out.println("  - list");
        System.out.println("  - mark <task number>");
        System.out.println("  - unmark <task number>");
        System.out.println("  - todo <description>");
        System.out.println("  - deadline <description> /by <yyyy-MM-dd> <optional: HHmm>");
        System.out.println("  - event <description> " +
                "/from <yyyy-MM-dd> <optional: HHmm> /to <yyyy-MM-dd> <optional: HHmm>");
        System.out.println("  - delete <task number>");
        System.out.println("  - find <keyword>");
        System.out.println("  - on <yyyy-MM-dd>");
        System.out.println("  - help");
        System.out.println("  - bye");
    }

    /**
     * Prints a horizontal divider line for readability and formatting.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints a formatted error message.
     *
     * @param message the error details to show
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Prints the last command entered by the user (for debugging/feedback).
     *
     * @param input the raw command string
     */
    public void showEntered(String input) {
        System.out.println(" Command entered: " + input);
    }

    /**
     * Prints all tasks in the list with 1-based indices.
     *
     * @param tasks the task list to display
     */
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

    /**
     * Prints the tasks that matched a search query.
     * If the list is empty, prints a friendly "no matches" message.
     * Otherwise, prints the matching tasks with 1-based indices.
     *
     * @param matches the task list containing only the matching tasks
     */
    public void showMatchingTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println(" No matching tasks found!!");
            return;
        }
        System.out.println(" Searching for matching tasks... >.<");
        System.out.println(" Here are the tasks in your list that match your keyword:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
    }

    /**
     * Prints the details of a newly added task and the updated total count.
     *
     * @param t     the task added
     * @param count the total number of tasks after the addition
     */
    public void showTaskAdded(Task t, int count) {
        System.out.println(" Got it!! I've added this task:");
        System.out.println("   " + t);
        System.out.println(" Now you have " + count + " tasks in your list.");
    }

    /**
     * Prints the details of a removed task and the updated total count.
     *
     * @param t     the task removed
     * @param count the total number of tasks after the removal
     */
    public void showTaskRemoved(Task t, int count) {
        System.out.println(" Meow!! I've removed this task:");
        System.out.println("   " + t);
        System.out.println(" Now you have " + count + " tasks in the list:)");
    }

    /**
     * Prints a confirmation that a task was marked as done.
     *
     * @param t the task that was marked as done
     */
    public void showMarked(Task t) {
        System.out.println(" Yay! I've meowed this task as done:");
        System.out.println(" " + t);
    }

    /**
     * Prints a confirmation that a task was marked as not done.
     *
     * @param t the task that was unmarked
     */
    public void showUnmarked(Task t) {
        System.out.println(" Aww okay... I've meowed this task as not done yet:");
        System.out.println(" " + t);
    }
}
