/**
 * This class handles the various default message outputs
 * of the Duke program.
 */
public class Ui {
    /**
     * Returns the greeting message display on startup.
     */
    public String getWelcomeMessage() {
        return "    Hello! I'm Duke!\n" + "    What can I do for you today?\n";
    }

    /**
     * Returns the farewell message when user exits the application.
     */
    public String getExitMessage() {
        return "    Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns the message output when the user adds a task to the list.
     * @param task The task to be added.
     * @param list The working task list.
     * @return The message output on adding the task.
     */
    public String getAddedMessage(Task task, TaskList list) {
        String output = "";
        output += "    Got it. I've added this task:\n";
        output += "      " + task.getTaskDisplay() + "\n";
        output += "    Now you have " + list.size() + " task"
                + ((list.size() == 1) ? "" : "s") + " in the list.\n";
        return output;
    }

    /**
     * @return The message output when a user input error
     *         leads to an Index Exception.
     */
    public String getIndexExceptionMessage() {
        return "    Oops! The task you referred to is not on the list.\n"
                + "    Please refer to the list using the 'list' command.\n";
    }

    /**
     * @return The message output when a user input error
     *         leads to a Number Format Exception.
     */
    public String getNumFormatExceptionMessage() {
        return "    Oops! This command must be followed by a task number.\n";
    }
}
