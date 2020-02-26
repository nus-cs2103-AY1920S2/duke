package duke;

import java.util.ArrayList;

/**
 * UI handles what user sees on display.
 */
public class Ui {

    public Ui() {
    }

    public String format = "    ----------------------------------------------\n";

    /**
     * Returns greeting message.
     */
    public String showGreeting() {
        return (format + "      Aloha, I'm Stitch!\n      What can I do for you? :)\n" + format);
    }

    /**
     * Returns loading error message if data loading error occurs.
     */
    public String showLoadingError() {
        return (format + "      Oops! Something went wrong :(\n" + format);
    }

    /**
     * Returns goodbye message when program terminates.
     */
    public String showGoodbye() {
        return (format + "      Aloha! Hope to see you again soon! :)\n" + format);
    }

    /**
     * Returns list of tasks in user's task list.
     *
     * @param taskList of user tasks
     */
    public String showList(TaskList taskList) {
        String response = (format + "      Here are the tasks in your list:\n");

        for (int i = 1; i < taskList.tasks.size() + 1; i++) {
            Task current = taskList.tasks.get(i - 1);
            response += ("      " + i + ". " + current + "\n");
        }
        response += format;

        return response;
    }

    /**
     * Returns confirmation message when user marks a task as done.
     *
     * @param task to be marked done
     */
    public String showDone(Task task) {
        return (format
                + "      Nice! I've marked this task as done:\n "
                + "        " + task + "\n"
                + format);
    }

    /**
     * Returns list of tasks found with matching keyword.
     *
     * @param taskList of found tasks
     */
    public String showFound(ArrayList<Task> taskList) {
        String response = "";

        if (taskList.isEmpty()) {
            response = (format + "      Sorry, there are no matching tasks :(\n");
        } else {
            response = (format + "      Here are the matching tasks in your list:\n");

            for (int i = 1; i < taskList.size() + 1; i++) {
                Task current = taskList.get(i - 1);
                response += ("      " + i + ". " + current + "\n");
            }
        }
        response += format;

        return response;
    }

    /**
     * Returns error message when user enters invalid task number.
     */
    public String showTaskError() {
        return (format + "      Sorry, this task does not exist :(\n" + format);
    }

    /**
     * Returns confirmation message when user adds a new task.
     *
     * @param task added
     * @param tasks ArrayList of user tasks
     */
    public String showTaskAdded(Task task, ArrayList<Task> tasks) {
        return (format
                + "      Got it. I've added this task:\n"
                + "        " + task + "\n"
                + "      Now you have " + tasks.size() + " tasks in the list.\n"
                + format);
    }

    /**
     * Returns confirmation message when user deletes a task.
     *
     * @param task deleted
     * @param tasks ArrayList of user tasks
     */
    public String showTaskDeleted(Task task, ArrayList<Task> tasks) {
        return (format
                + "      I've removed this task:\n "
                + "        " + task + "\n"
                + "      Now you have " + tasks.size() + " tasks in the list.\n"
                + format);
    }

    /**
     * Returns error message when user enters invalid date format.
     */
    public String showDateError() {
        return (format
                + "      Please enter a valid date in this format:\n"
                + "      YYYY-MM-DD !\n"
                + format);
    }

    /**
     * Returns error message when user enters invalid date and time format.
     */
    public String showDateTimeError() {
        return (format
                + "      Please enter a valid date & time in this format:\n"
                + "      YYYY-MM-DDTHH:MM !\n"
                + format);
    }

    /**
     * Returns error message when user enters more than 1 task number.
     *
     * @return error message
     */
    public String showValidError() {
        return format + "      Please enter a only one Task number!\n" + format;
    }

    /**
     * Returns error message when user does not enter any task number.
     *
     * @return error message
     */
    public String showNumError() {
        return format + "      Please enter a Task number!\n" + format;
    }

    /**
     * Returns error message when user does not enter task description.
     *
     * @return error message
     */
    public String showDescriptionError() {
        return format + "      Please enter a Task description!\n" + format;
    }

    /**
     * Returns error message when user enters invalid action.
     *
     * @return error message
     */
    public String showActionError() {
        return format + "      Sorry, I didn't understand that :(\n"
                + "Please try again!\n" + format;
    }

    /**
     * Returns error message when user has entered duplicate task
     *
     * @return error message
     */
    public String showDuplicateError() {
        return format + "      You have this task in the list!\n"
                + "      Please enter a different description\n" + format;
    }
}
