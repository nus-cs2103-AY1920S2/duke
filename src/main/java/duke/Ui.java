package duke;

import java.io.IOException;

/**
 * Performs basic 'interaction' with the user.
 */
public class Ui {
    public static final String LF = "\r\n";
    private static final String WELCOME_MSG = "Hello! I'm duke.Duke" + LF + "What can I do for you?" + LF;
    public static final String BYE_MSG = "Bye, hope to see you again soon!" + LF;
    public static final String HELP_MSG = "Here are the list of supported commands:" + LF
            + "list - Lists all Tasks" + LF
            + "done [Task index] - Marks the specified task as done" + LF
            + "delete [Task index] - Deletes the specified task" + LF
            + "todo [Task name] - Creates a new Task" + LF
            + "deadline [Task name] [date] - Creates a new (Deadline) Task" + LF
            + "event [Task name] [date] - Creates a new (Event) Task" + LF
            + "find [Task name] - Finds a Task based on a certain name keyword" + LF
            + "help - Prints this Help message" + LF
            + "bye - Exits the application" + LF;

    /**
     * Constructs a new UI object.
     */
    public Ui() {
    }

    /**
     * Prints the welcome message.
     *
     * @return The string to be printed.
     */
    public String printWelcomeMsg() {
        return WELCOME_MSG;
    }

    /**
     * Print the bye message.
     */
    public String printByeMsg() {
        return BYE_MSG;
    }

    /**
     * Print the bye message.
     */
    public String printHelpMsg() {
        return HELP_MSG;
    }

    /**
     * Reads and trim a raw user command, for parsing.
     *
     * @return A trimmed version of the user command.
     * @throws IOException If there's an IO error which has occurred.
     */
    public String readCmd(String cmd) throws IOException {
        return cmd.trim();
    }

    /**
     * Prints the content using a line feed thereafter.
     *
     * @param line Content to be printed.
     * @return The string to be printed.
     */
    public String printLine(String line) {
        return line + LF;
    }

    /**
     * Prints a blank line.
     *
     * @return The string to be printed.
     */
    public String printLine() {
        return LF;
    }

    /**
     * Print the Task which has been added to the task-list.
     *
     * @param t Task to add to the task-list.
     * @param taskList The task-list containing all currently added Tasks.
     * @return The string to be printed.
     */
    public String printAddedTask(Task t, TaskList taskList) {
        return this.printLine("Got it! I've added this task:" + Ui.LF + "    " + t + Ui.LF
                + "Now, you have " + taskList.getNumTasks() + " item(s) in your list." + Ui.LF);
    }

    /**
     * Prints all Tasks in the task-list.
     *
     * @param taskList The current task-list containing all Tasks.
     * @return The string to be printed.
     */
    public String printTaskList(TaskList taskList) {
        String result = this.printLine("Here are your task(s):");

        int len = taskList.getNumTasks();

        for (int i = 0; i < len; ++i) {
            Task t = taskList.getTask(i);
            result += this.printLine("    " + (i + 1) + ". " + t);
        }

        result += this.printLine();

        return result;
    }

    /**
     * Prints all Tasks found in the task-list.
     *
     * @param taskList The current task-list containing all Tasks.
     * @return The string to be printed.
     */
    public String printFoundTaskList(TaskList taskList) {
        String result = this.printLine("Here are the matching task(s) your list:");

        int len = taskList.getNumTasks();

        for (int i = 0; i < len; ++i) {
            Task t = taskList.getTask(i);
            result += this.printLine("    " + (i + 1) + ". " + t);
        }

        result += this.printLine();

        return result;
    }

    /**
     * Prints feedback on Tasks being marked as done.
     *
     * @param t Task being marked as done.
     * @return The string to be printed.
     */
    public String printTaskMarkedDone(Task t) {
        return this.printLine("Nice! I've marked this task as done:" + Ui.LF + "    " + t + Ui.LF);
    }

    /**
     * Prints feedback on the Task being deleted from the task-list.
     *
     * @param t Task which has been removed.
     * @param taskList The current task-list representing all Tasks currently in the list.
     * @return The string to be printed.
     */
    public String printTaskDeleted(Task t, TaskList taskList) {
        return this.printLine("Noted! I've removed this task:" + Ui.LF + "    " + t + Ui.LF
                + "Now, you have " + taskList.getNumTasks() + " item(s) in your list." + Ui.LF);
    }
}
