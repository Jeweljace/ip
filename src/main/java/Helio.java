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
                int taskNum = Integer.parseInt(input.split(" ")[1]);
                userList[taskNum - 1].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println(" " + userList[taskNum - 1]);
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("unmark ")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]);
                userList[taskNum - 1].markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println(" " + userList[taskNum - 1]);
                System.out.println("______________________________________________________________");
            } else {
                userList[count] = new Task(input);
                count++;
                System.out.println("____________________________________________________________\n" +
                        "added: " + input + "\n" +
                        "____________________________________________________________");
            }

        }
    }
}
