package duke.interaction;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private String logo = "   _   _   _   _   _   _  \n"
            + "  / \\ / \\ / \\ / \\ / \\ / \\ \n"
            + " ( D | O | B | B | Y )\n"
            + "  \\_/ \\_/ \\_/ \\_/ \\_/ \n";

    public Ui() {
        
    }

    /**
     * Greets user.
     * @return string format of greeting
     */
    public String sayHi() {
        return String.format("Hello I'm \n%s\nWhat can I do for you?", logo);
    }

    /**
     * Greets user and ends session.
     * @return string format of goodbye
     */
    public String sayBye() {
        return "Dobby has no master. Dobby is freeee now!";
    }

    /**
     * Introduces list.
     * @return string format of list intro
     */
    public String list() {
        return "Here are the tasks in your list:\n";
    }

    /**
     * Informs user of deleted task.
     * @param deleted task that has been deleted from list.
     * @param size total number of tasks.
     * @return string format of deleted task
     */
    public String delete(Task deleted, int size) {
        return String.format("Noted. I've removed this task:\n%s\n "
                + "Now you have %d tasks in the list.\n", deleted, size);
    }

    /**
     * Marks current task as done.
     * @param curr task to be marked as done.
     * @return string format of done notification
     */
    public String done(Task curr) {
        return String.format("Nice! I've marked this task as done: \n%s\n", curr.toString());
    }

    /**
     * Prints intro for found tasks.
     * @param list list of found tasks.
     * @return String format of intro to list of items
     */
    public String found(ArrayList list) {
        if (!list.isEmpty()) {
            return "Here are the matching tasks in your list:\n";
        } else {
            return "There are no matching tasks in your list :(\n";
        }
    }

    /**
     * Prints error when file fails to load.
     * @return string error info
     */
    public String showLoadingError() {
        return "OOPS! Previous tasklist failed to load. Let's start afresh!";
    }
}
