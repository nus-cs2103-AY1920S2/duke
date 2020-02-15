package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.List;

public class Ui {
    /** The logo for Duke. */
    private static final String HEADER = ""
            + "    PSYCHIATRIC    \n"
            + "      HELP 5¢      \n\n"
            + "     c o _ o ɔ     \n\n"
            + "    THE  DOCTOR    \n"
            + "     IS [ IN ]     ";

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
        return HEADER;
    }

    /**
     * Displays an error message in the chat-bot.
     *
     * @param e the error encountered.
     * @return an error message in the chat-bot.
     */
    public String getError(DukeException e) {
        String message = "AAUGH!!\n";
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
        showText("Okie! I've added this task:\n\n" + indent(task.toString(), 2));
    }

    /**
     * Logs a completed task.
     *
     * @param task the completed task.
     */
    public void showDone(Task task) {
        String praise = "Good job completing this task!\n"
                + "Here's a tick for completing it!\n\n";

        showText(praise + indent(task.toString(), 2));
    }

    /**
     * Logs a list of all tasks.
     *
     * @param tasks the list of tasks to display.
     */
    public void showList(TaskList tasks) {
        showText("Here are the tasks in your list:\n\n" + tasks.toString());
    }

    /**
     * Logs a deleted task.
     *
     * @param task the deleted task.
     */
    public void showDelete(Task task) {
        showText("Alright! I'll remove this task:\n\n" + indent(task.toString(), 2));
    }

    /**
     * Logs the number of tasks in the list.
     *
     * @param tasks the task list to count and display.
     */
    public void showTaskCount(TaskList tasks) {
        showText("There are now " + tasks.getNumTasks() + " tasks in the list.");
    }

    /** Logs a farewell to the user. */
    public void showGoodbye() {
        showText("Five cents, please!");
    }

    /**
     * Logs a search result.
     *
     * @param tasks the list of tasks that was searched.
     * @param taskIds the list of ids in the task list matching the search criteria.
     */
    public void showFind(TaskList tasks, List<Integer> taskIds) {
        // TODO: exception if taskIds contains duplicate elements
        String title = "Here are the matching tasks in your list:\n\n";

        StringBuilder foundTasks = new StringBuilder();

        for (int i = 0; i < taskIds.size(); i++) {
            int taskId = taskIds.get(i);
            foundTasks.append(tasks.getFormattedTask(taskId)).append("\n");
        }

        if (foundTasks.length() > 0) {
            foundTasks.deleteCharAt(foundTasks.length() - 1);
        }

        showText(title + foundTasks.toString());
    }

    /** Displays a line in the chat-bot. */
    public void showLine() {
        int lineWidth = 60;
        String lineSymbol = "-";

        response += indent(lineSymbol.repeat(lineWidth), 4);
    }

    /** Displays text in the chat-bot. */
    public void showText(String text) {
        response += text;
    }

    /**
     * Adds an indent to all lines of a given text.
     *
     * @param text the text to indent.
     * @param indentWidth the character width of the indentation.
     * @return the indented text.
     */
    private String indent(String text, int indentWidth) {
        assert indentWidth > 0 : "Should be adding at least one whitespace"
                + " of indentation, not " + indentWidth;

        String indent = " ".repeat(indentWidth);
        return text.replaceAll("(?m)^", indent);
    }
}