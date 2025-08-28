import java.util.Scanner;

public class Helio {
    public static void main(String[] args) {
        String[] userList = new String[100];
        int count = 0;
        String logo = "        ╱|\n" +
                "       (˚ˎ 。7\n" +
                "        |、˜〵\n" +
                "       じしˍ,)ノ\n";
        System.out.println("____________________________________________________________\n" +
                "Hello... I'm Helio o.o\n" +
                logo +
                "What can I do for you? ._. \n" +
                "____________________________________________________________\n");

        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                break;
            } else if (input.equals("list")) {
                for (String element : userList) {
                    if (element != null) {
                        System.out.println(element);
                    }
                }
            } else {
                userList[count] = count + 1 + ". " + input;
                count++;
                System.out.println("____________________________________________________________\n" +
                        "added: " + input + "\n" +
                        "____________________________________________________________\n");
            }

        }
    }
}
