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
     * Method prints greeting message.
     */
    public String showGreeting() {
        return (format + "      Aloha, I'm Stitch!\n      What can I do for you? :)\n" + format);
    }

    /**
     * Method prints loading error if data loading error occurs.
     */
    public String showLoadingError() {
        return (format + "      Oops! Something went wrong :(\n" + format);
    }

    /**
     * Method prints goodbye message when program terminates.
     */
    public String showGoodbye() {
        return (format + "      Bye. Hope to see you again soon! :)\n" + format);
    }

    /**
     * Method prints list of tasks in user's task list.
     *
     * @param taskList
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
     * Method prints confirmation message when user marks a task as done.
     *
     * @param task
     */
    public String showDone(Task task) {
        return (format
                + "      Nice! I've marked this task as done:\n "
                + "        " + task + "\n"
                + format);
    }

    /**
     * Method prints list of tasks found with matching keyword.
     *
     * @param taskList
     */
    public String showFound(ArrayList<Task> taskList) {
        String response = "";
        response = (format + "      Here are the matching tasks in your list:\n");

        for (int i = 1; i < taskList.size() + 1; i++) {
            Task current = taskList.get(i - 1);
            response += ("      " + i + ". " + current + "\n");
        }
        response += format;

        return response;
    }

    /**
     * Method prints error message when user enters invalid task number.
     */
    public String showTaskError() {
        return (format + "      Sorry, this task does not exist :(\n" + format);
    }

    /**
     * Method prints confirmation message when user adds a new task.
     *
     * @param task
     * @param tasks
     */
    public String showTaskAdded(Task task, ArrayList<Task> tasks) {
        return (format
                + "      Got it. I've added this task:\n"
                + "        " + task + "\n"
                + "      Now you have " + tasks.size() + " tasks in the list.\n"
                + format);
    }

    /**
     * Method prints confirmation message when user deletes a task.
     * @param task
     * @param tasks
     */
    public String showTaskDeleted(Task task, ArrayList<Task> tasks) {
        return (format
                + "      I've removed this task:\n "
                + "        " + task + "\n"
                + "      Now you have " + tasks.size() + " tasks in the list.\n"
                + format);
    }

    /**
     * Method prints error message when user enters invalid date format.
     */
    public String showDateError() {
        return (format
                + "      Please enter a date in this format: YYYY-MM-DD !\n"
                + format);
    }

    /**
     * Method prints error message when user enters invalid date and time format.
     */
    public String showDateTimeError() {
        return (format
                + "      Please enter a date & time in this format: YYYY-MM-DDTHH:MM !\n"
                + format);
    }

    /**
     * Method prints error message when user enters more than 1 task number.
     *
     * @return
     */
    public String showValidError() {
        return format + "      Please enter a only one Task number!\n" + format;
    }

    /**
     * Method prints error message when user does not enter any task number.
     *
     * @return
     */
    public String showNumError() {
        return format + "      Please enter a Task number!\n" + format;
    }

    /**
     * Method prints error message when user does not enter task description.
     *
     * @return
     */
    public String showDescriptionError() {
        return format + "      Please enter a Task description!\n" + format;
    }

    /**
     * Method prints error message when user enters invalid action.
     *
     * @return
     */
    public String showActionError() {
        return format + "      Sorry, I didn't understand that :( Please try again.\n" + format;
    }
}
