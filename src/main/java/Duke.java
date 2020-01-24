import java.util.*;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        reply("What can I do for you");

        Scanner sc = new Scanner(System.in);
        String command;
        while (!(command = sc.nextLine()).equals("bye")) {
            handle(command);
        }
        reply("Bye. Hope to see you again soon!");
    }

    public static void handle(String string) {
        if (string.equals("list")) {
            printList();
        } else {
            Task task = new Task(string);
            tasks.add(task);
            reply("added: " + string);
        }
    }

    public static void reply(String string) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + string);
        System.out.println("    ____________________________________________________________");
    }

    public static void printList() {
        System.out.println("    ____________________________________________________________");
        int count = 1;
        for (Task task : tasks) {
            System.out.println("    " + count + "." + task.action);
            count++;
        }
        System.out.println("    ____________________________________________________________");
    }
}
