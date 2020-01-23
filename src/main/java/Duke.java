import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a simulation of a chat bot called Duke.
 */
public class Duke {
    public static void main(String[] args) {
        /** Scanner of user input */
        Scanner reader = new Scanner(System.in);
        /** Task list of all tasks */
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
                        try {
                            markDone(tasks.get(Integer.valueOf(words[1]) - 1));
                        } catch (IndexOutOfBoundsException e) {
                            printBreak();
                            System.out.println("    OOP!!! The number of tasks you have is only " + tasks.size());
                            printBreak();
                        }
                        break;
                    case "delete":
                        try {
                            delete(tasks.get(Integer.valueOf(words[1]) - 1), tasks);
                        } catch (IndexOutOfBoundsException e) {
                            printBreak();
                            System.out.println("    OOP!!! The number of tasks you have is only " + tasks.size());
                            printBreak();
                        }
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
                        try {
                            add(new Deadline(ddlDetails[0], ddlDetails[1]), tasks);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            printBreak();
                            System.out.println("    OOP!!! The Deadline time is incorrect.");
                            printBreak();
                        }
                        break;
                    case "event":
                        String[] eventDetails = getTaskDetails(input.substring(6),
                                " /at ");
                        try {
                            add(new Event(eventDetails[0], eventDetails[1]), tasks);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            printBreak();
                            System.out.println("    OOP!!! The event time is incorrect.");
                            printBreak();
                        }
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

    /**
     * Print logo of Duke.
     */
    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printBreak();
    }

    /**
     * Print break line.
     */
    private static void printBreak() {
        System.out.println("    ____________________________________________________");
    }

    /**
     * Print greeting sentences.
     */
    private static void printGreet() {
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        printBreak();
    }

    /**
     * Echo what user input and print it out.
     *
     * @param input User input.
     */
    private static void echo(String input) {
        printBreak();
        System.out.println("    " + input);
        printBreak();
    }

    /**
     * Print ending sentences.
     */
    private static void exit() {
        printBreak();
        System.out.println("    Bye. Hope to see you again soon!");
        printBreak();
    }

    /**
     * List out all tasks with number, type and description.
     *
     * @param tasks All tasks.
     */
    private static void list(ArrayList<Task> tasks) {
        printBreak();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.get(i - 1);
            System.out.println("    " + i + "." + currTask);
        }
        printBreak();
    }

    /**
     * Add new task into task list and print its type and description.
     * Print current number of total tasks after addition.
     *
     * @param t New task.
     * @param tasks All task.
     */
    private static void add(Task t, ArrayList<Task> tasks) {
        printBreak();
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + t);
        tasks.add(t);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        printBreak();
    }

    /**
     * Mark one task's status as done.
     *
     * @param currTask Current task that needs to be marked.
     */
    private static void markDone(Task currTask) {
        printBreak();
        System.out.println("    Nice! I've marked this task as done:");
        currTask.markAsDone();
        System.out.println("      " + currTask);
        printBreak();
    }

    /**
     * Returns one task's description and time separately.
     *
     * @param str String contains all task details.
     * @param splitter Splitter of description part and time part.
     * @return 2-elements String array, which contains description and time.
     */
    private static String[] getTaskDetails(String str, String splitter) {
        String[] details = str.split(splitter);
        return details;
    }

    /**
     * Delete certain task from task list.
     * Print current number of tasks after deletion.
     *
     * @param currTask the task that needs deletion.
     * @param tasks All tasks.
     */
    private static void delete(Task currTask, ArrayList<Task> tasks) {
        printBreak();
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + currTask);
        tasks.remove(currTask);
        System.out.println("    sNow you have " + tasks.size() + " tasks in the list.");
        printBreak();
    }
}
