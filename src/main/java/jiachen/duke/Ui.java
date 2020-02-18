package jiachen.duke;

/**
 * The Ui class handles the view and presentation layer of the app.
 */
public class Ui {
    /**
     * Format string.
     *
     * @param message the message
     * @return the string
     */
    public String format(String message) {
        return formatSeparator()
            + "\t"
            + message
            + "\n"
            + formatSeparator();
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
     * Format remove task string.
     *
     * @param task the task
     * @return the string
     */
    public String formatRemoveTask(Task task) {
        return formatSeparator()
            + "\t Noted. I've removed this task:\t\t"
            + task + "\n"
            + formatSeparator();
    }

    /**
     * Format done task string.
     *
     * @param task the task
     * @return the string
     */
    public String formatDoneTask(Task task) {
        return formatSeparator()
            + "\t Nice! I've marked this task as done: \n\t\t"
            + task
            + formatSeparator();
    }

    /**
     * Format tasks string.
     *
     * @param tasks the tasks
     * @return the string
     */
    public String formatTasks(TaskList tasks) {
        StringBuilder builder = new StringBuilder(formatSeparator());
        for (int i = 1; i <= tasks.getList().size(); i++) {
            builder.append("\t ").append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        builder.append(formatSeparator());
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
        return formatSeparator()
            + "\t Got it. I've added this task: \n"
            + "\t\t"
            + task
            + "\n"
            + "\t Now you have "
            + numOfTasks
            + " tasks in the list.\n" + formatSeparator();
    }

    /**
     * Format loading error string.
     *
     * @return the string
     */
    public String formatLoadingError() {
        return "\tERR: unable to load file from disk!\n";
    }

    private String formatSeparator() {
        return "\t_____________________________________________________";
    }

    /**
     * Format filtered tasks string.
     *
     * @param tasks the tasks
     * @return the string
     */
    public String formatFilteredTasks(TaskList tasks) {
        StringBuilder builder = new StringBuilder(formatSeparator());
        builder.append("\n\tHere are the matching tasks in your list:\n");

        for (int i = 1; i <= tasks.getList().size(); i++) {
            builder.append("\t ").append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        builder.append(formatSeparator());
        return builder.toString();
    }

    /**
     * Format help message.
     *
     * @return help info as a string
     */
    public String formatHelp() {
        return "Here are a list of commands you can try! "
            + "\n\thello - say hello to duke"
            + "\n\ttodo - to create a new todo task"
            + "\n\tevent - to create a new event task"
            + "\n\tdeadline - to create a new deadline task"
            + "\n\tdone - to mark a task as done"
            + "\n\tdelete - to delete am existing task"
            + "\n\tlist - to list all tasks"
            + "\n\tfind - find certain tasks"
            + "\n\thelp - to get help information"
            + "\n\texit - to leave duke";
    }

    /**
     * Format Hello message.
     *
     * @return hello message as a string
     */
    public String formatHello() {
        return "Hello there, my name is DUKE and I'm here to help you get organized! "
            + "To see what I can help you with you could try the `help` command.";
    }
}
