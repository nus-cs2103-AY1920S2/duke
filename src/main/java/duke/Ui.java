package duke;

import java.util.ArrayList;

/**
 * UI handles what user sees on display.
 */
public class Ui {
    protected String format = "    -----------------------------------------------------------------\n";

    /**
     * Method prints greeting message.
     */
    public void showGreeting() {
        String logo =
                "      ____        _        \n"
                        + "     |  _ \\ _   _| | _____ \n"
                        + "     | | | | | | | |/ / _ \\\n"
                        + "     | |_| | |_| |   <  __/\n"
                        + "     |____/ \\__,_|_|\\_\\___|\n";

        System.out.println(format + "      Hello! I'm\n" + logo);
        System.out.println("      What can I do for you? :)\n" + format);
    }

    /**
     * Method prints loading error if data loading error occurs.
     */
    public void showLoadingError() {
        System.out.println(format + "      Oops! Something went wrong :(\n" + format);
    }

    /**
     * Method prints goodbye message when program terminates.
     */
    public void showGoodbye() {
        System.out.println(format + "      Bye. Hope to see you again soon! :)\n" + format);
    }

    /**
     * Method prints list of tasks in user's task list.
     *
     * @param taskList
     */
    public void showList(TaskList taskList) {
        System.out.println(format + "      Here are the tasks in your list:");

        for (int i = 1; i < taskList.tasks.size() + 1; i++) {
            Task current = taskList.tasks.get(i - 1);
            System.out.println("      " + i + ". " + current);
        }
        System.out.println(format);
    }

    /**
     * Method prints confirmation message when user marks a task as done.
     *
     * @param task
     */
    public void showDone(Task task) {
        System.out.println(format
                + "      Nice! I've marked this task as done:\n "
                + "        " + task + "\n"
                + format);
    }

    /**
     * Method prints error message when user enters invalid task number.
     */
    public void showTaskError() {
        System.out.println(format + "      Sorry, this task does not exist :(\n" + format);
    }

    /**
     * Method prints confirmation message when user adds a new task.
     *
     * @param task
     * @param tasks
     */
    public void showTaskAdded(Task task, ArrayList<Task> tasks) {
        System.out.println(format
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
    public void showTaskDeleted(Task task, ArrayList<Task> tasks) {
        System.out.println(format
                + "      I've removed this task:\n "
                + "        " + task + "\n"
                + "      Now you have " + tasks.size() + " tasks in the list.\n"
                + format);
    }

    /**
     * Method prints error message when user enters invalid date format.
     */
    public void showDateError() {
        System.out.println(format
                + "      Please enter a date in this format: YYYY-MM-DD !\n"
                + format);
    }

    /**
     * Method prints error message when user enters invalid date and time format.
     */
    public void showDateTimeError() {
        System.out.println(format
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
