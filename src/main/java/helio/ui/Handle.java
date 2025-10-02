package helio.ui;

public final class Handle {
    private Handle() {
    }

    private static final String LINE = "____________________________________________________________";

    public static void invalidCommand() {
        System.out.println(LINE);
        System.out.println(" Sorry, invalid input :< Please try again. See help for list of valid inputs!");
        System.out.println(LINE);
    }

    public static void missingTaskNumber() {
        System.out.println(LINE);
        System.out.println(" Meow! Please enter a task number!! ");
        System.out.println(LINE);
    }

    public static void invalidTaskNumber() {
        System.out.println(LINE);
        System.out.println(" Meow! Invalid task number!");
        System.out.println(LINE);
    }

    public static void emptyTodoDescription() {
        System.out.println(LINE);
        System.out.println(" Meow! Description of todo cannot be empty! =.=");
        System.out.println(LINE);
    }

    public static void emptyDeadlineParts() {
        System.out.println(LINE);
        System.out.println(" Meow! Please fill in a description and a date/time! =.=");
        System.out.println(LINE);
    }

    public static void emptyEventParts() {
        System.out.println(LINE);
        System.out.println(" Meow! Please fill in a description and date/time of start and end! =.=");
        System.out.println(LINE);
    }
}
