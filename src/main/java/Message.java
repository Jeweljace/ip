public final class Message {
    private Message() {
    }

    private static final String LINE = "____________________________________________________________";

    public static void greet(String logo) {
        System.out.println(LINE);
        System.out.println(" Hello... I'm Helio o.o");
        System.out.println(logo);
        System.out.println(" What can I do for you? ._.");
        System.out.println(LINE);
    }

    public static void bye() {
        System.out.println(LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void help() {
        System.out.println(LINE);
        System.out.println(" List of valid inputs:");
        System.out.println(" 1. list (view current list of tasks)");
        System.out.println(" 2. mark <task number> (mark a task as done)");
        System.out.println(" 3. unmark <task number> (mark a task as not done)");
        System.out.println(" 4. todo <task description> (add a task without any date/time attached to it)");
        System.out.println(" 5. deadline <task description> /by <deadline>");
        System.out.println(" 6. event <task description> /from <date/time> /to <date/time>");
        System.out.println(" 7. help (shows list of valid commands)");
        System.out.println(" 8. bye (exits program)");
        System.out.println(LINE);
    }

    public static void listHeader() {
        System.out.println(LINE);
        System.out.println(" Here are the tasks in your list:");
    }

    public static void listFooter() {
        System.out.println(LINE);
    }

    public static void emptyList() {
        System.out.println(LINE);
        System.out.println(" Nothing for meow ^.^");
        System.out.println(LINE);
    }

    public static void markedDone(String task) {
        System.out.println(LINE);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + task);
        System.out.println(LINE);
    }

    public static void markedUndone(String task) {
        System.out.println(LINE);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println(" " + task);
        System.out.println(LINE);
    }

    public static void addedTask(String task, int count) {
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println(LINE);
    }

    public static void showEntered(String input) {
        System.out.println(" Command entered: " + input);
    }
}
