import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI class supports everything that is displayed to the user.
 */
public class UI {

    Scanner scanner;
    /**
     * Empty constructor.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * String of asterisks for design purposes.
     */
    private static String horizontalLine = "**********************************************";

    private static final String HELPLIST =
            "help - lists out available commands\n" +
            "list - lists out all the tasks\n" +
            "todo <task name> - adds a ToDo task\n" +
            "event <task name> /by <event date: YYYY-MM-DD> - adds an Event task\n" +
            "deadline <task name> /by <event date: YYYY-MM-DD> - adds a Deadline task\n" +
            "done <task number> - marks indicated task as complete\n" +
            "delete <task number> - deletes the indicated task\n" +
            "find <keyword> - searches for tasks with <keyword>\n" +
            "bye - saves and closes the program";

    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints the introduction logo when program is first started.
     */
    static void printIntro() {
        System.out.println(horizontalLine);
        String logo =
                " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello, I'm Duke \n" + "How can I help you today?");
        System.out.println("Type 'help' to list available commands");
        System.out.println(horizontalLine);
    }

    /**
     * Prints the goodbye message when user inputs "bye".
     */
    public void printBye() {
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }


    public void printAdd(Task task, TaskList tasks) {
        System.out.println(horizontalLine);
        System.out.println("I have added " + task);
        System.out.println("You now have " + tasks.getTaskList().size() + " tasks!");
        System.out.println(horizontalLine);
    }

    /**
     * Prints out the tasks in the list.
     */
    public void printList(TaskList tasks) {
        ArrayList<Task> list = tasks.getTaskList();
        System.out.println(horizontalLine);
        if (list.isEmpty()) {
            System.out.println("You have 0 outstanding tasks!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (Task s : list) {
                int listNo = list.indexOf(s) + 1;
                System.out.println(listNo + "." + s);
            }
        }
        System.out.println(horizontalLine);
    }

    public void printDone(Task task, int taskNumber, TaskList tasks) {
        System.out.println(horizontalLine);
        //I have marked 1. <name> as done!
        System.out.println("I have marked " + taskNumber + ". " + task + " as done!");
        System.out.println("You now have " + tasks.getTaskList().size() + " tasks left!");
        System.out.println(horizontalLine);
    }

    public void printDelete(Task task, int taskNumber, TaskList tasks) {
        System.out.println(horizontalLine);
        //I have deleted <task>
        System.out.println("I have deleted " + taskNumber + ". " + task);
        System.out.println("You now have " + tasks.getTaskList().size() + " tasks remaining!");
        System.out.println(horizontalLine);
    }

    /**
     * Prints out the tasks found.
     * @param list Takes in a list to print out.
     */
    public void printFoundTasks(ArrayList<Task> list) {
        System.out.println(horizontalLine);
        if (list.isEmpty()) {
            System.out.println("There were no tasks found");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (Task s : list) {
                int listNo = list.indexOf(s) + 1;
                System.out.println(listNo + "." + s);
            }
        }
        System.out.println(horizontalLine);
    }

    public void printDuplicate(Task task) {
        System.out.println(horizontalLine);
        System.out.println("The task '" + task.getTaskName() + "' you are adding already exists!");
        System.out.println(horizontalLine);
    }

    public void printHelp() {
        System.out.println(HELPLIST);
    }
}
