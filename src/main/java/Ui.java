import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with user.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Class constructor.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints greeting upon start up.
     */
    public void printGreeting() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you\n");
    }

    /**
     * Prints error when file to be read from cannot be found.
     */
    public void showLoadingError() {
        System.out.println("File cannot be found");
    }

    /**
     * Reads the user command.
     *
     * @return User command.
     */
    public String readTask() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Prints the list of tasks currently saved.
     *
     * @param tasks List of saved tasks.
     */
    public void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");

        for (int i = 1; i < tasks.getSize() + 1; i++) {
            System.out.println(i + "." + tasks.getTask(i - 1).obtainTaskInfo());
        }
        System.out.println();
    }

    /**
     * Prints the list of tasks that contains keyword.
     *
     * @param targets List of tasks that matches keyword.
     */
    public void printTargets(ArrayList<String> targets) {
        System.out.println("Here are the matching tasks in your list:");

        for (int i = 1; i < targets.size() + 1; i++) {
            System.out.println(i + "." + targets.get(i - 1));
        }
        System.out.println();
    }

    /**
     * Prints message to notify user when a task is successfully marked as done.
     *
     * @param tasks List of tasks.
     * @param completedTaskIndex Index of task marked as done.
     */
    public void printDoneSuccess(TaskList tasks, int completedTaskIndex) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.getTask(completedTaskIndex).obtainTaskInfo() + "\n");
    }

    /**
     * Prints exception when file update failed.
     *
     * @param exception Exception thrown.
     */
    public void printUpdateError(IOException exception) {
        System.out.println("Something went wrong: " + exception.getMessage());
    }

    /**
     * Prints message to notify user when a task is successfully deleted from list.
     *
     * @param tasks List of tasks.
     * @param removeTaskIndex Index of task deleted.
     */
    public void printDeleteSuccess(TaskList tasks, int removeTaskIndex) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tasks.getTask(removeTaskIndex).obtainTaskInfo());
    }

    /**
     * Prints the number of tasks in the list.
     *
     * @param tasks List of tasks.
     */
    public void printStatusUpdate(TaskList tasks) {
        if (tasks.getSize() == 1) {
            System.out.println("Now you have " + tasks.getSize() + " task in the list.\n");
        } else {
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.\n");
        }
    }

    /**
     * Prints exception message when DukeException is thrown.
     *
     * @param exception Exception thrown.
     */
    public void printException(DukeException exception) {
        System.out.println(exception);
    }

    /**
     * Prints message to notify user when a task is successfully added to the list.
     *
     * @param tasks List of tasks.
     */
    public void printAddSuccess(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.getTask(tasks.getSize() - 1).obtainTaskInfo());
    }

    /**
     * Prints greeting when exiting.
     */
    public void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
