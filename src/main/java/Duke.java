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
            String[] inputArr = input.split(" ", 2);
            switch(inputArr[0]) {
                case "todo":
                    addTask(new Todo(inputArr[1]));
                    break;
                case "deadline":
                    String[] deadlineArr = inputArr[1].split(" /by ");
                    addTask(new Deadline(deadlineArr[0], deadlineArr[1]));
                    break;
                case "event":
                    String[] eventArr = inputArr[1].split(" /at ");
                    addTask(new Event(eventArr[0], eventArr[1]));
                    break;
                case "done":
                    int taskId = Integer.parseInt(inputArr[1]);
                    Task task = tasks.get(taskId - 1);
                    task.markAsDone();
                    print("Nice! I've marked this task as done: \n  " + task);
                    break;
                case "list":
                    printTasks();
                    break;
                default:
                    print("Invalid input.");
            }
            input = sc.nextLine();
        }
        print("Bye. Hope to see you again soon!");
        sc.close();
    }

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println(indent + horizontal_line);
        System.out.println(indent + "Got it. I've added this task: ");
        System.out.println(indent + "  " + task);
        System.out.println(indent + "Now you have " + Task.getNumOfTasks() + " tasks in the list.");
        System.out.println(indent + horizontal_line + "\n");
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
