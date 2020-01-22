import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.printf("Hello! I'm Duke\nWhat can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        String command = sc.next();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.printf("list\n");
            } else if (command.equals("blah")) {
                System.out.printf("blah\n");
            }
            command = sc.next();
        }

        System.out.printf("Bye. Hope to see you again soon!\n");
        sc.close();
    }
}
