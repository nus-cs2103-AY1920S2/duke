package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represent the user interface for the program to prepare reply string to user.
 */
public class Ui {
    private static final String LS = System.lineSeparator();
    private static final String NUM_OF_TASKS = "Now you have %d tasks in the list.";
    private static final String ADD_TASK = "Got it. I've added this task: ";
    private static final String DELETE_TASK = "Noted. I've removed this task: ";
    private static final String DONE_TASK = "Nice! I've marked this task as done: ";
    private static final String EXIT_TASK = "Bye!!! See you again :)";
    private static final String INVALID_TASK = "Invalid command format!";
    private static final String FIND_TASK = "Here are the matching tasks in your list:";

    public Ui() {

    }

    /**
     * Returns message to the user.
     *
     * @param message message given
     */
    private String showToUser(String... message) {
        StringBuilder formatted = new StringBuilder();
        for (String m : message) {
            formatted.append(m).append(LS);
        }
        return formatted.toString();
    }

    /**
     * Returns error message based on the exception to user.
     *
     * @param ex exception which contain error message
     */
    public String showError(DukeException ex) {
        return showToUser(ex.getMessage());
    }

    /**
     * Returns the information of added task to user.
     *
     * @param task new task
     * @param totalTasks current total task in integer
     */
    public String showAddTask(Task task, int totalTasks) {
        return showToUser(ADD_TASK, task.toString(), String.format(NUM_OF_TASKS, totalTasks));
    }

    /**
     * Returns the information of deleted task to user.
     *
     * @param task deleted task
     * @param totalTasks current total task in integer
     */
    public String showDeleteTask(Task task, int totalTasks) {
        return showToUser(DELETE_TASK, task.toString(), String.format(NUM_OF_TASKS, totalTasks));
    }

    /**
     * Returns the information of done task to user.
     *
     * @param task done task
     */
    public String showDone(Task task) {
        return showToUser(DONE_TASK, task.toString());
    }

    /**
     * Returns exit message to user.
     */
    public String showExit() {
        return showToUser(EXIT_TASK);
    }

    /**
     * Returns invalid command message to user.
     *
     * @param message message of invalid command
     */
    public String showInvalid(String message) {
        return showToUser(INVALID_TASK, message);
    }

    /**
     * Returns all current tasks to user.
     *
     * @param tasks list of all tasks
     */
    public String showAllTask(TaskList tasks) {
        return showToUser(getAllTaskForView(tasks));
    }

    /**
     * Returns all find tasks to user.
     *
     * @param tasks list of find tasks
     */
    public String showFindTask(TaskList tasks) {
        return showToUser(FIND_TASK, getAllTaskForView(tasks));
    }

    /**
     * Format list of task to the format which can used by the showToUser.
     *
     * @param tasks list of all tasks
     * @return string represent list of tasks
     */
    private String getAllTaskForView(TaskList tasks) {
        StringBuilder formatted = new StringBuilder();
        int displayIndex = 1;
        for (Task task : tasks.getList()) {
            formatted.append(displayIndex).append(task.toString()).append("\n");
            displayIndex++;
        }
        if (formatted.length() != 0) {
            formatted.setLength(formatted.length() - 1);
        }
        return formatted.toString();
    }
}
