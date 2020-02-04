package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.List;

public class Ui {
    /** The logo for Duke. */
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /** The previous response from Duke. */
    private String response;

    /** Constructs a new Ui object for console input and output. */
    public Ui() {
        this("");
    }

    /** Constructs a new Ui object for console input and output. */
    private Ui(String response) {
        this.response = response;
    }

    /** Clears the log of responses from Duke. */
    public void clear() {
        response = "";
    }

    /**
     * Returns the latest response from Duke.
     *
     * @return the latest response from Duke.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Displays a welcome message in the chat-bot.
     *
     * @return a welcome message in the chat-bot.
     */
    public String getWelcome() {
        String header = indent("Hello from\n" + LOGO, 4);
        return header;
    }

    /**
     * Displays an error message in the chat-bot.
     *
     * @param e the error encountered.
     * @return an error message in the chat-bot.
     */
    public String getError(DukeException e) {
        String message = "☹ OOPS!!!\n";
        return message + e.getMessage();
    }

    /**
     * Displays the loading error message in the chat-bot.
     *
     * @return the loading error message in the chat-bot.
     */
    public String getLoadingError() {
        return "Sorry... the task list could not be loaded from a save file.\n\n"
                + "Here's a new task list for you to use.";
    }

    /**
     * Logs a task that has been added to the list.
     *
     * @param task the tasks that has been added to the list.
     */
    public void showAdd(Task task) {
        String confirmation = "Okie! I've added this task:\n\n";
        response += confirmation + indent(task.toString(), 2);
    }

    /**
     * Logs a completed task.
     *
     * @param task the completed task.
     */
    public void showDone(Task task) {
        String praise = "Good job completing this task!\n"
                + "Here's a tick for completing it!\n\n";

        response += praise + indent(task.toString(), 2);
    }

    /**
     * Logs a list of all tasks.
     *
     * @param tasks the list of tasks to display.
     */
    public void showList(TaskList tasks) {
        String title = "Here are the tasks in your list:\n\n";
        response += title + tasks.toString();
    }

    /**
     * Logs a deleted task.
     *
     * @param task the deleted task.
     */
    public void showDelete(Task task) {
        String comment = "Alright! I'll remove this task:\n\n";
        response += comment + indent(task.toString(), 2);
    }

    /**
     * Logs the number of tasks in the list.
     *
     * @param tasks the task list to count and display.
     */
    public void showTaskCount(TaskList tasks) {
        String count = "There are now " + tasks.getNumTasks() + " tasks in the list.";
        response += count;
    }

    /** Logs a farewell to the user. */
    public void showGoodbye() {
        response += "Bye! Please give a review if you like this program!";
    }

    /**
     * Logs a search result.
     *
     * @param tasks the list of tasks that was searched.
     * @param taskIds the list of ids in the task list matching the search criteria.
     */
    public void showFind(TaskList tasks, List<Integer> taskIds) {
        String title = "Here are the matching tasks in your list:\n\n";
        StringBuilder foundTasks = new StringBuilder();

        for (int i = 0; i < taskIds.size(); i++) {
            int taskId = taskIds.get(i);
            foundTasks.append(tasks.getFormattedTask(taskId)).append("\n");
        }
        response += title + foundTasks.toString();
    }

    /** Displays a line in the chat-bot. */
    public void showLine() {
        int lineWidth = 60;
        String lineSymbol = "-";

        response += indent(lineSymbol.repeat(lineWidth), 4);
    }

    /**
     * Displays a line break in the chat-bot.
     *
     * @param numLines the number of line breaks to display.
     */
    public void showLineBreak(int numLines) {
        response += "\n".repeat(numLines);
    }

    /**
     * Adds an indent to all lines of a given text.
     *
     * @param text the text to indent.
     * @param indentWidth the character width of the indentation.
     * @return the indented text.
     */
    private String indent(String text, int indentWidth) {
        String indent = " ".repeat(indentWidth);
        return text.replaceAll("(?m)^", indent);
    }
}