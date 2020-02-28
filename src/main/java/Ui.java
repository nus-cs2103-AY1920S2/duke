import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the user interface. This Ui class outputs messages and interacts with the user.
 */
public class Ui {

    /**
     * Creates a new Ui.
     */
    public Ui() { }

    /**
     * Greets to the user.
     */
    public void greet() {
        System.out.println("Hello! I'm Duke\n What can I do for you?");
    }

    /**
     * Farewell to the user.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Adds a task for the user.
     */
    public void add(Task task, ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:\n" + task.toString()
                + "\n Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Marks a task as done for the user.
     */
    public void done(int n, ArrayList<Task> tasks) {
        System.out.println("Nice! I've marked this task as done: \n"
                + tasks.get(n-1).toString());
    }

    /**
     * Deletes a task for the user.
     */
    public void delete(int n, ArrayList<Task> tasks) {
        System.out.println("Noted. I've removed this task:\n"
                + tasks.get(n-1).toString());
    }

    /**
     * Finds tasks that matches the given keyword for the user.
     * @Param results The list of matching tasks.
     */
    public void findKeyword(ArrayList<Task> results) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i) != null) {
                System.out.println((i + 1) + ". " + results.get(i).toString());
            }
        }
    }

    /**
     * Counts the number of task in the current list for the user.
     */
    public void count(ArrayList<Task> tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Shows command error for the user while catching duke exceptions.
     */
    public void showCommandError(DukeException e) {
        if (e.getType().equals("EmptyToDo")) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        }
        if (e.getType().equals("EmptyDeadline")) {
            System.out.println("OOPS!!! The description of a deadline cannot be empty.");
        }
        if (e.getType().equals("EmptyEvent")) {
            System.out.println("OOPS!!! The description of a event cannot be empty.");
        }
        if (e.getType().equals("invalid")) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Shows loading error for the user while files are not found.
     */
    public void showLoadingError() {
        System.out.println("File not found");
    }

    /**
     * Shows error for the user while catching IOExceptions.
     */
    public void showIOException(IOException e) {
        System.out.println("Oops! " + e.getMessage());
    }
}