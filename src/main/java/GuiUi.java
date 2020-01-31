import java.io.IOException;
import java.util.ArrayList;

public class GuiUi {

    /**
     * Returns greeting to be printed on UI upon start-up.
     * @return greeting to be printed.
     */
    public String getGreeting() {
        return "Hello! I'm Duke\nWhat can I do for you\n";
    }

    public String getExitGreeting() {
        return "Bye. Hope to see you again soon!";
    }

    public String getList(TaskList tasks) {
        String response = "Here are the tasks in your list:\n";

        for (int i = 1; i < tasks.getSize() + 1; i++) {
            response = response.concat(i + "." + tasks.getTask(i - 1).obtainTaskInfo() + "\n");
        }
        return response;
    }

    public String getDoneSuccess(TaskList tasks, int completedTaskIndex) {
        String response = "Nice! I've marked this task as done:\n";
        response = response.concat("  " + tasks.getTask(completedTaskIndex).obtainTaskInfo() + "\n");

        return response;
    }

    /**
     * Returns exception formatted as a string when file update failed.
     *
     * @param exception Exception thrown.
     */
    public String getUpdateError(IOException exception) {
        return "Something went wrong: " + exception.getMessage() + "\n";
    }

    /**
     * Returns message to notify user when a task is successfully deleted from list.
     *
     * @param tasks List of tasks.
     * @param removeTaskIndex Index of task deleted.
     */
    public String getDeleteSuccess(TaskList tasks, int removeTaskIndex) {
        String response = "Noted. I've removed this task:\n";
        response = response.concat("  " + tasks.getTask(removeTaskIndex).obtainTaskInfo() + "\n");

        return response;
    }

    /**
     * Returns the number of tasks in the list formatted in a string.
     *
     * @param tasks List of tasks.
     */
    public String getStatusUpdate(TaskList tasks) {
        if (tasks.getSize() == 1) {
            return "Now you have " + tasks.getSize() + " task in the list.\n";
        } else {
            return "Now you have " + tasks.getSize() + " tasks in the list.\n";
        }
    }

    /**
     * Returns exception message when DukeException is thrown.
     *
     * @param exception Exception thrown.
     */
    public String getExceptionMessage(DukeException exception) {
        return exception.getMessage() + "\n";
    }

    /**
     * Returns message to notify user when a task is successfully added to the list.
     *
     * @param tasks List of tasks.
     */
    public String getAddSuccess(TaskList tasks) {
        String response = "Got it. I've added this task:\n";
        response = response.concat("  " + tasks.getTask(tasks.getSize() - 1).obtainTaskInfo() + "\n");

        return response;
    }

    /**
     * Returns error when file to be read from cannot be found.
     */
    public String getLoadingError() {
        return "File cannot be found\n";
    }

    /**
     * Gets the list of tasks that contains keyword formatted in a string.
     *
     * @param targets List of tasks that matches keyword.
     */
    public String getTargets(ArrayList<String> targets) {
        String response = "Here are the matching tasks in your list:\n";

        for (int i = 1; i < targets.size() + 1; i++) {
            response = response.concat(i + "." + targets.get(i - 1) + "\n");
        }
        return response;
    }
}
