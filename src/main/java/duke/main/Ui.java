package duke.main;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import java.util.ArrayList;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Generates welcome message when user first enters app.
     *
     * @return String representation of welcome message
     */
    public static String showWelcome() {
        return "Hello! I'm Brian-bot :)\nWhat can I do for you?\nFor list of commands, type: /help";
    }

    /**
     * Generates goodbye message after user calls 'bye' to close the app.
     *
     * @return String representation of goodbye message
     */
    public String showGoodbye() {
        return "Goodbye! See you again soon! :)";
    }

    /**
     * Generates this bare bones manual page when user calls '/help'.
     *
     * @return String representation of list of commands
     */
    public String showHelp() {
        return "You called for help? Help is here!\nList of commands:"
                + "\n\ntodo, deadline, event, done, delete, list, find, bye"
                + "\n\nEnter any command to see its use";
    }

    /**
     * Generates this message after a task is marked as done.
     *
     * @param task Name of task(s) to be marked as done
     * @param numOfDone Number of tasks to be marked as done
     * @return String representation of success message after task is marked as done
     */
    public String showDone(String task, int numOfDone) {
        assert numOfDone > 0 : "Invalid use of done command";
        if (numOfDone == 1) {
            return "Nice! I've marked this task as done:\t" + task;
        } else {
            return "Nice! I've marked these tasks as done:\t" + task;
        }
    }

    /**
     * Generates this message after a task is deleted.
     *
     * @param task Name of task to be deleted
     * @param sizeOfList Size of the task list after deletion
     * @param numOfDelete Number of tasks to be deleted
     * @return String representation of success message after task is deleted
     */
    public String showDelete(String task, int sizeOfList, int numOfDelete) {
        assert numOfDelete > 0 : "Invalid use of delete command";
        if (numOfDelete == 1) {
            return "Noted. I've removed this task:\t" + task
                    + "\nNow you have " + sizeOfList + " tasks in the list.";
        } else {
            return "Noted. I've removed these tasks:\t" + task
                    + "\nNow you have " + sizeOfList + " tasks in the list.";
        }
    }

    /**
     * Generates this message after a to-do task, deadline, or event is added to the task list.
     *
     * @param task Name of task that was added
     * @param sizeOfList Size of the task list after adding
     * @return String representation of success message after a task is added to the task list
     */
    public String showSuccessMessage(String task, int sizeOfList) {
        return "Got it. I've added this task:\n\t" + task + "\nNow you have "
                + sizeOfList + " tasks in the list.";
    }

    /**
     * Turns on countdown timer and generates task list for list command.
     *
     * @param list Task list
     * @return String representation of task list with countdown timer
     */
    public String showListTimerOn(ArrayList<Task> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:");
        String toAppend;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Deadline) {
                toAppend = "\n\t" + (i + 1) + ". " + ((Deadline) list.get(i)).displayDeadline();
            } else if (list.get(i) instanceof Event) {
                toAppend = "\n\t" + (i + 1) + ". " + ((Event) list.get(i)).displayEventTime();
            } else {
                toAppend = "\n\t" + (i + 1) + ". " + list.get(i);
            }
            sb.append(toAppend);
        }
        sb.append("\n\nTip: Try using 'list /showtimer' or list '/hidetimer'!");
        return sb.toString();
    }

    /**
     * Turns off countdown timer and generates task list for list command.
     *
     * @param list Task list
     * @return String representation of task list without countdown timer
     */
    public String showListTimerOff(ArrayList<Task> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:");
        String toAppend;
        for (int i = 0; i < list.size(); i++) {
            toAppend = "\n\t" + (i + 1) + ". " + list.get(i);
            sb.append(toAppend);
        }
        sb.append("\n\nTip: Try using 'list /showtimer' or list '/hidetimer'!");
        return sb.toString();
    }

    /**
     * Turns on countdown timer and generates task list for find command.
     *
     * @param list Task list
     * @return String representation of a list consisting of tasks that contain the keyword(s) (with countdown timer)
     */
    public String showFindListTimerOn(ArrayList<Task> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:");
        if (list.isEmpty()) {
            sb.append("\n\tNo matches found!");
        } else {
            String toAppend;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof Deadline) {
                    toAppend = "\n\t" + (i + 1) + ". " + ((Deadline) list.get(i)).displayDeadline();
                } else if (list.get(i) instanceof Event) {
                    toAppend = "\n\t" + (i + 1) + ". " + ((Event) list.get(i)).displayEventTime();
                } else {
                    toAppend = "\n\t" + (i + 1) + ". " + list.get(i);
                }
                sb.append(toAppend);
            }
        }
        return sb.toString();
    }

    /**
     * Turns off countdown timer and generates task list for find command.
     *
     * @param list Task list
     * @return String representation of a list consisting of tasks that contain the keyword(s) (without countdown timer)
     */
    public String showFindListTimerOff(ArrayList<Task> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:");
        if (list.isEmpty()) {
            sb.append("\n\tNo matches found!");
        } else {
            String toAppend;
            for (int i = 0; i < list.size(); i++) {
                toAppend = "\n\t" + (i + 1) + ". " + list.get(i);
                sb.append(toAppend);
            }
        }
        return sb.toString();
    }

    /**
     * Generates this message when task list is empty and user wants to view list.
     *
     * @return String representation of an empty task list
     */
    public String showEmptyListMessage() {
        return "\tHere are the tasks in your list:\n\n\tYou have no tasks "
                + "right now.\n\tUse 'todo', 'deadline', or 'event' to add task!";
    }

    /**
     * Generates error message.
     *
     * @param errorMessage Error message to display
     * @return String representation of an error message
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }
}
