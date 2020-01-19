import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static List<Task> tasks;

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        tasks = new ArrayList<>();
        String input = sc.nextLine();
        while (true) {
            switch (input) {
            case "bye":
                exit();
                break;
            case "list":
                list();
                break;
            default:
                addTask(input);
                break;
            }
            input = sc.nextLine();
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

    private static void addTask(String description) {
        tasks.add(new Task(description));
        System.out.println(style("added: " + description));
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

    private static String style(String message) {
        String horizontalLine = new String(new char[76]).replace("\0", "-") + "\n";
        message = horizontalLine + message + "\n" + horizontalLine;
        return message.lines()
            .map(x -> "    " + x + "\n")
            .reduce("", (x, y) -> x + y);
    }
}
