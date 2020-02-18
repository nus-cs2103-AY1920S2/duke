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

    private static final String HELP_LIST = "1. help - lists out available commands\n"
            + "2. list - lists out all the tasks\n"
            + "3. todo <task name> - adds a ToDo task\n"
            + "4. event <task name> /by <event date: YYYY-MM-DD> - adds an Event task\n"
            + "5. deadline <task name> /by <event date: YYYY-MM-DD> - adds a Deadline task\n"
            + "6. done <task number> - marks indicated task as complete\n"
            + "7. delete <task number> - deletes the indicated task\n"
            + "8. find <keyword> - searches for tasks with <keyword>\n"
            + "9. bye - saves and closes the program";

    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints the introduction logo when program is first started.
     */
    static void printIntro() {
        String logo =
                " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello, I'm Duke \n" + "How can I help you today?");
        System.out.println("Type 'help' to list available commands");
    }

    /**
     * Prints the goodbye message when user inputs "bye".
     */
    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }


    public void printAdd(Task task, TaskList tasks) {
        System.out.println("I have added " + task);
        System.out.println("You now have " + tasks.getTaskList().size() + " tasks!");
    }

    /**
     * Prints out the tasks in the list.
     */
    public void printList(TaskList tasks) {
        ArrayList<Task> list = tasks.getTaskList();
        if (list.isEmpty()) {
            System.out.println("You have 0 outstanding tasks!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (Task s : list) {
                int listNo = list.indexOf(s) + 1;
                System.out.println(listNo + "." + s);
            }
        }
    }

    /**
     * Prints out task as done.
     *
     * @param task Task to be marked as done.
     * @param taskNumber Index of the task.
     * @param tasks Task list where task can be found.
     */
    public void printDone(Task task, int taskNumber, TaskList tasks) {
        System.out.println("I have marked " + taskNumber + ". " + task + " as done!");
        System.out.println("You now have " + tasks.getTaskList().size() + " tasks left!");
    }

    /**
     * Prints out task as deleted.
     *
     * @param task Task to be deleted.
     * @param taskNumber Index of the task.
     * @param tasks Task list where the task can be found.
     */
    public void printDelete(Task task, int taskNumber, TaskList tasks) {
        //I have deleted <task>
        System.out.println("I have deleted " + taskNumber + ". " + task);
        System.out.println("You now have " + tasks.getTaskList().size() + " tasks remaining!");
    }

    /**
     * Prints out the tasks found.
     *
     * @param list Takes in a list to print out.
     */
    public void printFoundTasks(ArrayList<Task> list) {
        if (list.isEmpty()) {
            System.out.println("There were no tasks found");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (Task s : list) {
                int listNo = list.indexOf(s) + 1;
                System.out.println(listNo + "." + s);
            }
        }
    }

    /**
     * Prints out that the task is duplicated.
     * @param task The duplicated task.
     */
    public void printDuplicate(Task task) {
        System.out.println("The task '" + task.getTaskName() + "' you are adding already exists!");
    }

    /**
     * Prints out the HELP_LIST.
     */
    public void printHelp() {
        System.out.println(HELP_LIST);
    }
}
