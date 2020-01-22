import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String line = "    ____________________________________________________________";
    private static final String space = "    ";
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputs = input.split(" ", 2);
            String command = inputs[0];
            if (command.equals("bye")) {
                sayGoodbye();
                break;
            } else if (command.equals("list")) {
                displayList();
            } else if (command.equals("done")) {
                int taskNo = Integer.parseInt(inputs[1]);
                markTaskAsDone(taskNo);
            } else if (command.equals("deadline")){
                addDeadline(inputs[1]);
            } else if (command.equals("event")) {
                addEvent(inputs[1]);
            } else if (command.equals("todo")) {
                addTodo(inputs[1]);
            } else {
                unrecognisedCommand();
            }
        }
    }

    private static void printLine() {
        System.out.println(line);
    }

    private static void indent(String toIndent) {
        System.out.printf(space);
        System.out.println(toIndent);
    }

    private static void greet() {
        printLine();
        String logo = space
                + " ____        _        \n" + space
                + "|  _ \\ _   _| | _____ \n" + space
                + "| | | | | | | |/ / _ \\\n" + space
                + "| |_| | |_| |   <  __/\n" + space
                + "|____/ \\__,_|_|\\_\\___|\n";
        indent("Greetings, you may call me\n" + logo);
        indent("How may I help you in this fine day today?");
        printLine();
    }

    private static void sayGoodbye() {
        printLine();
        indent("I bid you adieu. Until the day we meet again.");
        printLine();
    }

    private static void displayList() {
        printLine();
        for (int i = 0; i < tasks.size(); i++) {
            indent((i + 1) + ". " +tasks.get(i).toString());
        }
        printLine();
    }

    private static void unrecognisedCommand() {
        printLine();
        indent("Unfortunately, I do not recognise what you have entered. ");
        indent("Please try again.");
        printLine();
    }

    private static void markTaskAsDone(int taskNo) {
        Task task = tasks.get(taskNo - 1); // The user starts counting from 1
        task.markAsDone();

        printLine();
        indent("Excellent! You have completed this task: ");
        indent(space + task.toString());
        printLine();
    }

    private static void printTaskCount() {
        int len = tasks.size();
        if (len == 0 || len == 1) {
            indent("As of now, you have " + len + " task in the list.");
        } else {
            indent("As of now, you have " + len + " tasks in the list.");
        }
    }

    private static void addDeadline(String args) {
        String[] descAndBy = args.split(" /by ");
        Task deadline = new Deadline(descAndBy[0], descAndBy[1]);
        tasks.add(deadline);
        printLine();
        indent("Acknowledged. I have added: ");
        indent(space + deadline.toString());
        printTaskCount();
        printLine();
    }

    private static void addEvent(String args) {
        String[] descAndAt = args.split(" /at ");
        Task event = new Event(descAndAt[0], descAndAt[1]);
        tasks.add(event);
        printLine();
        indent("Acknowledged. I have added: ");
        indent(space + event.toString());
        printTaskCount();
        printLine();
    }

    private static void addTodo(String args) {
        Task todo = new Todo(args);
        tasks.add(todo);
        printLine();
        indent("Acknowledged. I have added: ");
        indent(space + todo.toString());
        printTaskCount();
        printLine();
    }


}
