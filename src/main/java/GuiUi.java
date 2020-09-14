import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles the interactions with user through GUI.
 */
public class GuiUi {

    /**
     * Returns greeting to be printed on GUI upon start-up.
     *
     * @return Greeting to be printed.
     */
    public String getGreeting() {
        return "Hello! I'm Duke\nWhat can I do for you\n";
    }

    /**
     * Returns greeting to be printed on GUI upon shut-down.
     *
     * @return Greeting to be printed.
     */
    public String getExitGreeting() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the list of tasks formatted into a single string for printing on GUI.
     *
     * @param tasks List of tasks.
     * @return List of tasks properly formatted for printing.
     */
    public String getList(TaskList tasks) {
        String response = "Here are the tasks in your list:\n";

        for (int i = 1; i < tasks.getSize() + 1; i++) {
            response = response.concat(i + "." + tasks.getTask(i - 1).obtainTaskInfo() + "\n");
        }
        return response;
    }

    /**
     * Returns the success message once a task is successfully marked as done.
     *
     * @param tasks List of tasks.
     * @param completedTaskIndex Task that was marked completed.
     * @return Success message once a task is successfully marked as done.
     */
    public String getDoneSuccess(TaskList tasks, int completedTaskIndex) {
        String response = "Nice! I've marked this task as done:\n";
        response = response.concat("  " + tasks.getTask(completedTaskIndex).obtainTaskInfo() + "\n");

        return response;
    }

    /**
     * Returns exception formatted as a string when file update failed.
     *
     * @param exception Exception thrown.
     * @return Exception message.
     */
    public String getUpdateError(IOException exception) {
        return "Something went wrong: " + exception.getMessage() + "\n";
    }

    /**
     * Returns success message when a task is successfully deleted from list.
     *
     * @param tasks List of tasks.
     * @param removeTaskIndex Index of task deleted.
     * @return Success message.
     */
    public String getDeleteSuccess(TaskList tasks, int removeTaskIndex) {
        String response = "Noted. I've removed this task:\n";
        response = response.concat("  " + tasks.getTask(removeTaskIndex).obtainTaskInfo() + "\n");

        return response;
    }

    /**
     * Returns the number of tasks in the list formatted in a string for printing on GUI.
     *
     * @param tasks List of tasks.
     * @return Status update message.
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
     * @return Exception message.
     */
    public String getExceptionMessage(DukeException exception) {
        return exception.getMessage() + "\n";
    }

    /**
     * Returns success message when a task is successfully added to the list.
     *
     * @param tasks List of tasks.
     * @return Success message.
     */
    public String getAddSuccess(TaskList tasks) {
        String response = "Got it. I've added this task:\n";
        response = response.concat("  " + tasks.getTask(tasks.getSize() - 1).obtainTaskInfo() + "\n");

        return response;
    }

    /**
     * Returns the list of tasks that contains keyword formatted in a string for printing on GUI.
     *
     * @param targets List of tasks that matches keyword.
     * @return List of tasks that matches keyword.
     */
    public String getTargets(ArrayList<Task> targets) {
        if (targets.size() == 0) {
            return "There are no matching tasks.\n";
        }

        String response = "Here are the matching tasks in your list:\n";

        for (int i = 1; i < targets.size() + 1; i++) {
            response = response.concat(i + "." + targets.get(i - 1).obtainTaskInfo() + "\n");
        }
        return response;
    }

    public String getUndoIdentifier(String command) {
        return "[Undo " + command + " command]: ";
    }

    public String getReverseDoneSuccess(TaskList tasks, int reverseTaskIndex) {
        String response = "OH MAN! I've reversed the status of this task:\n";
        response = response.concat("  " + tasks.getTask(reverseTaskIndex).obtainTaskInfo() + "\n");

        return response;
    }
}
