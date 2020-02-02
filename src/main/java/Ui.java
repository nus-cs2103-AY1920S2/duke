import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles the interactions with the user as they input to Duke.
 */
public class Ui {
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public boolean hasInputs() {
        return sc.hasNext();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showFileNotFoundError() {
        System.out.println("This file is not found in the specified directory!! :(");
    }

    public void showIndexError() {
        System.out.println("This task option does not exist within the task list. Please try again.");
    }

    public void showLoadingError() {
        System.out.println("OOPS! An error occurred while loading from given file.");
    }

    public void endDuke() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showDoneTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

    public void showDeletedTask(TaskList tasks, int option) {
        System.out.println("Noted. I've removed this task: \n  " + tasks.arr.get(option - 1));
        System.out.println("Now you have " + (tasks.arr.size() - 1) + " tasks in the list.");
    }

    public void showAddedTask(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.arr.size() + " tasks in the list.");
    }

    public void showFoundTasks(ArrayList<Task> tasksFound) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasksFound.size(); i++) {
            System.out.println(tasksFound.get(i));
        }
    }

    public void printList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.arr.size(); i++) {
            System.out.println(i + 1 + ". " + list.arr.get(i));
        }
    }

    public void showDateError() {
        System.out.println("Please key in the date in the YYYY-MM-DD format instead!!!");
    }
}
