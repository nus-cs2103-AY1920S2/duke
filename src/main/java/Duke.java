import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final String indent = "    ";
    private static final String horizontal_line = "____________________________________________________________";

    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        print(logo + "\nHello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printTasks();
            } else if (input.substring(0, 4).equals("done")) {
                int taskId = Integer.parseInt(input.substring(5));
                Task task = tasks.get(taskId - 1);
                task.markAsDone();
                print("Nice! I've marked this task as done: \n  " + task);
            } else {
                tasks.add(new Task(input));
                print("added: " + input);
            }
            input = sc.nextLine();
        }
        print("Bye. Hope to see you again soon!");
        sc.close();
    }

    private static void print(String text) {
        String[] lines = text.split("\n");
        System.out.println(indent + horizontal_line);
        for (String line : lines) {
            System.out.println(indent + line);
        }
        System.out.println(indent + horizontal_line + "\n");
    }

    private static void printTasks() {
        System.out.println(indent + horizontal_line);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(indent + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println(indent + horizontal_line + "\n");
    }
}
