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
        ArrayList<String> newList = new ArrayList<String>();
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                for (int i = 0; i < newList.size(); i += 1) {
                    System.out.printf("%d. %s\n", i+1, newList.get(i));
                }
            } else {
                newList.add(command);
                System.out.printf("added: %s\n", command);
            }
            command = sc.nextLine();
        }

        System.out.printf("Bye. Hope to see you again soon!\n");
        sc.close();
    }
}
