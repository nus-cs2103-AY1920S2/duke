import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        printLogo();
        printGreet();

        String input = reader.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                list(tasks);
            } else {
                String[] words = input.split(" ");
                switch (words[0]) {
                    case "done":
                        markDone(tasks.get(Integer.valueOf(words[1]) - 1));
                        break;
                    case "todo":
                        try {
                            add(new Todo(input.substring(5)), tasks);
                        } catch (IndexOutOfBoundsException e) {
                            printBreak();
                            System.out.println("    OOPS!!! The description of a todo cannot be empty.");
                            printBreak();
                        }
                        break;
                    case "deadline":
                        String[] ddlDetails = getTaskDetails(input.substring(9),
                                " /by ");
                        add(new Deadline(ddlDetails[0], ddlDetails[1]), tasks);
                        break;
                    case "event":
                        String[] eventDetails = getTaskDetails(input.substring(6),
                                " /at ");
                        add(new Event(eventDetails[0], eventDetails[1]), tasks);
                        break;
                    default:
                        printBreak();
                        System.out.println("    OOPS!!! I'm sorry, but I don't know what that meas :-(");
                        printBreak();
                }
            }
            input = reader.nextLine();
        }

        exit();
    }

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printBreak();
    }

    private static void printBreak() {
        System.out.println("    ____________________________________________________");
    }

    private static void printGreet() {
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        printBreak();
    }

    private static void echo(String input) {
        printBreak();
        System.out.println("    " + input);
        printBreak();
    }

    private static void exit() {
        printBreak();
        System.out.println("    Bye. Hope to see you again soon!");
        printBreak();
    }

    private static void list(ArrayList<Task> tasks) {
        printBreak();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.get(i - 1);
            System.out.println("    " + i + "." + currTask);
        }
        printBreak();
    }

    private static void add(Task t, ArrayList<Task> tasks) {
        printBreak();
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + t);
        tasks.add(t);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        printBreak();
    }

    private static void markDone(Task currTask) {
        printBreak();
        System.out.println("    Nice! I've marked this task as done:");
        currTask.markAsDone();
        System.out.println("      " + currTask);
        printBreak();
    }

    private static String[] getTaskDetails(String str, String spliter) {
        String[] details = str.split(spliter);
        return details;
    }
}
