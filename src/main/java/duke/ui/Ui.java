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
    private static final String DELETE_TASK = "Noted. I've removed these tasks: ";
    private static final String DONE_TASK = "Nice! I've marked this task as done: ";
    private static final String EXIT_TASK = "Bye!!! See you again :)";
    private static final String INVALID_TASK = "Invalid command format!";
    private static final String FIND_TASK = "Here are the matching tasks in your list:";

    /**
     * Shows message to the user.
     *
     * @param message message given
     */
    private void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m.replace("\n", LS));
        }
    }

    /**
     * Shows error message based on the exception to user.
     *
     * @param ex exception which contain error message
     */
    public void showError(DukeException ex) {
        showToUser(ex.getMessage());
    }

    /**
     * Shows the information of added task to user.
     *
     * @param task new task
     * @param totalTasks current total task in integer
     */
    public void showAddTask(Task task, int totalTasks) {
        showToUser(ADD_TASK, task.toString(), String.format(NUM_OF_TASKS, totalTasks));
    }

    /**
     * Shows the information of deleted task to user.
     *
     * @param tasks deleted tasks
     * @param totalTasks current total task in integer
     */
    public void showDeleteTask(TaskList tasks, int totalTasks) {
        showToUser(DELETE_TASK, getAllTaskForView(tasks), String.format(NUM_OF_TASKS, totalTasks));
    }

    /**
     * Shows the information of done task to user.
     *
     * @param task done task
     */
    public void showDone(Task task) {
        showToUser(DONE_TASK, task.toString());
    }

    /**
     * Shows exit message to user.
     */
    public void showExit() {
        showToUser(EXIT_TASK);
    }

    /**
     * Shows all current tasks to user.
     *
     * @param tasks list of all tasks
     */
    public void showAllTask(TaskList tasks) {
        showToUser(getAllTaskForView(tasks));
    }

    /**
     * Shows all find tasks to user.
     *
     * @param tasks list of find tasks
     */
    public void showFindTask(TaskList tasks) {
        showToUser(FIND_TASK, getAllTaskForView(tasks));
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
