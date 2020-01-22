import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        printLine();
        System.out.println(logo);
        printLine();
        System.out.println("    Hey there! I'm Duke");
        System.out.println("    What can I do for you?");
        printLine();

        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        while (true) {
            String token = scanner.nextLine();
            String[] split = token.split(" ", 2);
            if (token.equals("bye")) {
                printLine();
                System.out.println("    Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (token.equals("list")) {
                printLine();
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("     " + (i + 1) + ". " + list.get(i));
                }
                printLine();
            } else if (split[0].equals("done")) {
                int num = Integer.parseInt(split[1]) - 1;
                list.get(num).markDone();
                printLine();
                System.out.println("     Nice! I've marked this task as done: ");
                System.out.println("       " + list.get(num));
                printLine();
            } else {
                list.add(new Task(token));
                printLine();
                System.out.println("    added: " + token);
                printLine();
            }
        }
    }
}
