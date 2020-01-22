import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static List<Task> tasks;

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        tasks = new ArrayList<>();
        String input = sc.nextLine().strip();
        while (true) {
            switch (input.split("\\s+")[0]) {
            case "bye":
                exit();
                break;
            case "list":
                list();
                break;
            case "done":
                int taskNumber = Integer.parseInt(input.split("\\s+")[1]);
                completeTask(taskNumber);
                break;
            case "todo":
                addTodo(input);
                break;
            case "deadline":
                addDeadline(input);
                break;
            case "event":
                addEvent(input);
                break;
            default:
                System.out.println(style("Oops! I don't know what that means :("));
                break;
            }
            input = sc.nextLine().strip();
        }
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello, I'm Duke!\n"
                + "What can I do for you?";
        System.out.println(logo + style(greeting));
    }

    private static void exit() {
        String bye = "Goodbye. Hope to see you again soon!";
        System.out.println(style(bye));
        System.exit(0);
    }

    private static void addTodo(String input) {
        String description = input.split("\\s+", 2)[1];
        Todo todo = new Todo(description);
        tasks.add(todo);
        printTask(todo);
    }

    private static void addDeadline(String input) {
        String[] info = input.split("\\s+", 2)[1].split("\\s*\\\\by\\s*");
        Deadline deadline = new Deadline(info[0], info[1]);
        tasks.add(deadline);
        printTask(deadline);
    }

    private static void addEvent(String input) {
        String[] info = input.split("\\s+", 2)[1].split("\\s*\\\\at\\s*");
        Event event = new Event(info[0], info[1]);
        tasks.add(event);
        printTask(event);
    }

    private static void printTask(Task task) {
        String numberOfTasks;
        if (tasks.size() == 1) {
            numberOfTasks = "There is now 1 task in the list.";
        } else {
            numberOfTasks = "There are now " + tasks.size() + " tasks in the list.";
        }
        System.out.println(style("Got it. I've added this task:\n  " + task + "\n" + numberOfTasks));
    }

    private static void list() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i > 0) {
                list.append("\n");
            }
            list.append((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(style(list.toString()));
    }

    private static void completeTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task completedTask = tasks.get(taskIndex).complete();
        tasks.set(taskIndex, completedTask);
        System.out.println(style("Nice! I've marked this task as done:\n  " + completedTask));
    }

    private static String style(String message) {
        String horizontalLine = new String(new char[76]).replace("\0", "-") + "\n";
        message = horizontalLine + message + "\n" + horizontalLine;
        return message.lines()
            .map(x -> "    " + x + "\n")
            .reduce("", (x, y) -> x + y);
    }
}
