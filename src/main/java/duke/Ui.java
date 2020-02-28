package duke;

import java.util.ArrayList;

/**
 * UI handles what user sees on display.
 */
public class Ui {

    public Ui() {
    }

    /**
     * Returns greeting message.
     */
    public String showGreeting() {
        return "Aloha, I'm Stitch!\nWhat can I do for you? :)\n";
    }

    /**
     * Returns loading error message if data loading error occurs.
     */
    public String showLoadingError() {
        return "Oops! Something went wrong :(\n";
    }

    /**
     * Returns goodbye message when program terminates.
     */
    public String showGoodbye() {
        return "Aloha! Hope to see you again soon! :)\n";
    }

    /**
     * Returns list of tasks in user's task list.
     *
     * @param taskList of user tasks
     */
    public String showList(TaskList taskList) {
        String response = "You have no tasks currently! Hooray!\n";

        if (taskList.tasks.size() != 0) {
            response = "Here are the tasks in your list:\n";

            for (int i = 1; i < taskList.tasks.size() + 1; i++) {
                Task current = taskList.tasks.get(i - 1);
                response += (i + ". " + current + "\n");
            }
        }

        return response;
    }

    /**
     * Returns confirmation message when user marks a task as done.
     *
     * @param task to be marked done
     */
    public String showDone(Task task) {
        return "Yay! I've marked this task as done:\n " + task + "\n";
    }

    /**
     * Returns list of tasks found with matching keyword.
     *
     * @param taskList of found tasks
     */
    public String showFound(ArrayList<Task> taskList) {
        String response = "";

        if (taskList.isEmpty()) {
            response = "Sorry, there are no matching tasks :(\n";
        } else {
            response = "Here are the matching tasks in your list:\n";

            for (int i = 1; i < taskList.size() + 1; i++) {
                Task current = taskList.get(i - 1);
                response += (i + ". " + current + "\n");
            }
        }

        return response;
    }

    /**
     * Returns error message when user enters invalid task number.
     */
    public String showTaskError() {
        return "Sorry, this task does not exist :(\n";
    }

    /**
     * Returns confirmation message when user adds a new task.
     *
     * @param task added
     * @param tasks ArrayList of user tasks
     */
    public String showTaskAdded(Task task, ArrayList<Task> tasks) {
        return "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Returns confirmation message when user deletes a task.
     *
     * @param task deleted
     * @param tasks ArrayList of user tasks
     */
    public String showTaskDeleted(Task task, ArrayList<Task> tasks) {
        return "I've removed this task:\n "
                + task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Returns error message when user enters invalid date format.
     */
    public String showDateError() {
        return "Please enter a valid date in this format:\n"
                + "          YYYY-MM-DD !\n";
    }

    /**
     * Returns error message when user enters invalid date and time format.
     */
    public String showDateTimeError() {
        return "Please enter a valid date & time in this format:\n"
                + "        YYYY-MM-DDTHH:MM !\n";
    }

    /**
     * Returns error message when user enters more than 1 task number.
     *
     * @return error message
     */
    public String showValidError() {
        return "Please enter a only one Task number!\n";
    }

    /**
     * Returns error message when user does not enter any task number.
     *
     * @return error message
     */
    public String showNumError() {
        return "Please enter a Task number!\n";
    }

    /**
     * Returns error message when user does not enter task description.
     *
     * @return error message
     */
    public String showDescriptionError() {
        return "Please enter a Task description!\n";
    }

    /**
     * Returns error message when user enters invalid action.
     *
     * @return error message
     */
    public String showActionError() {
        return "Sorry, I didn't understand that :(\nPlease try again!\n";
    }

    /**
     * Returns error message when user has entered duplicate task
     *
     * @return error message
     */
    public String showDuplicateError() {
        return " You have this task in the list!\nPlease enter a different description\n";
    }
}
