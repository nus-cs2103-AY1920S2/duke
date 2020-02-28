package jiachen.duke;

/**
 * The Ui class handles the view and presentation layer of the app.
 */
public class Ui {

    /**
     * Format Hello message.
     * Welcome message is sent to user when Duke is started.
     *
     * @return hello message as a string
     */
    public static String formatWelcomeMessage() {
        return "Hello there, my name is DUKE and I'm here to help you get organized! "
            + "To see what I can help you with you could try the `help` command.";
    }


    /**
     * Format error string.
     *
     * @param errorMessage the error message
     * @return the string
     */
    public String formatError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Format string.
     *
     * @param message the message
     * @return the string
     */
    public String format(String message) {
        return message + "\n";
    }

    /**
     * Format remove task string.
     *
     * @param task the task
     * @return the string
     */
    public String formatRemoveTask(Task task) {
        return "Noted. I've removed this task for you.:\n  " + task;
    }

    /**
     * Format done task string.
     *
     * @param task the task
     * @return the string
     */
    public String formatDoneTask(Task task) {
        return "Nice! I've marked this task as done: \n  " + task;
    }

    /**
     * Format tasks string.
     *
     * @param tasks the tasks
     * @return the string
     */
    public String formatTasks(TaskList tasks) {
        StringBuilder builder = new StringBuilder("Here is your tasklist!\n");
        for (int i = 1; i <= tasks.getList().size(); i++) {
            builder.append("  ").append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        return builder.toString();
    }

    /**
     * Format new task string.
     *
     * @param task       the task
     * @param numOfTasks the num of tasks
     * @return the string
     */
    public String formatNewTask(Task task, int numOfTasks) {
        return "Got it. I've added this task:"
            + "\n  "
            + task
            + "\n"
            + "Now you have "
            + numOfTasks
            + " tasks in the list.\n";
    }

    /**
     * Format loading error string.
     *
     * @return the string
     */
    public String formatLoadingError() {
        return "Error: unable to load file from disk!\n";
    }

    /**
     * Format filtered tasks string.
     *
     * @param tasks the tasks
     * @return the string
     */
    public String formatFilteredTasks(TaskList tasks) {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the matching tasks in your list:\n");

        for (int i = 1; i <= tasks.getList().size(); i++) {
            builder.append("  ").append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        return builder.toString();
    }

    /**
     * Format help message.
     *
     * @return help info as a string
     */
    public String formatHelp() {
        return "Here are a list of commands you can try! "
            + "\n  hello - say hello to duke"
            + "\n  todo - to create a new todo task"
            + "\n  event - to create a new event task"
            + "\n  deadline - to create a new deadline task"
            + "\n  done - to mark a task as done"
            + "\n  delete - to delete an existing task"
            + "\n  list - to list all tasks"
            + "\n  find - find certain tasks"
            + "\n  help - to get help information"
            + "\n  exit - to leave duke";
    }
}
