package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import org.w3c.dom.ls.LSException;

import java.util.Scanner;

/**
 * Represent the user interface for the program.
 */
public class Ui {
    private static final String WELCOME_MESSAGE = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "What can I do for you? :)\n";
    private static final String LINE_PREFIX = "  ";
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "_________________________________________________________________________";
    private static final String LOADING_ERROR = "OOPS!!! There have some problem loading the existing duke.tasks.";
    private static final String NUM_OF_TASKS = "Now you have %d tasks in the list.";
    private static final String ADD_TASK = "Got it. I've added this task: ";
    private static final String DELETE_TASK = "Noted. I've removed this task: ";
    private static final String DONE_TASK = "Nice! I've marked this task as done: ";
    private static final String EXIT_TASK = "Bye!!! See you again :)";
    private static final String INVALID_TASK = "Invalid command format!";
    private static final String FIND_TASK = "Here are the matching tasks in your list:";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Show message to the user.
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
     * Read command from user.
     *
     * @return string represent command
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Show welcome message to user.
     */
    public void showWelcome() {
        showToUser(WELCOME_MESSAGE);
    }

    /**
     * Show loading error to user.
     */
    public String showLoadingError() {
        return showToUser(LOADING_ERROR);
    }

    /**
     * Show a divider to user.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Show error message based on the exception to user.
     *
     * @param ex exception which contain error message
     */
    public String showError(DukeException ex) {
        return showToUser(ex.getMessage());
    }

    /**
     * Show the information of added task to user.
     *
     * @param task new task
     * @param totalTasks current total task in integer
     */
    public String showAddTask(Task task, int totalTasks) {
        return showToUser(ADD_TASK, task.toString(), String.format(NUM_OF_TASKS, totalTasks));
    }

    /**
     * Show the information of deleted task to user.
     *
     * @param task deleted task
     * @param totalTasks current total task in integer
     */
    public String showDeleteTask(Task task, int totalTasks) {
        return showToUser(DELETE_TASK, task.toString(), String.format(NUM_OF_TASKS, totalTasks));
    }

    /**
     * Show the information of done task to user.
     *
     * @param task done task
     */
    public String showDone(Task task) {
        return showToUser(DONE_TASK, task.toString());
    }

    /**
     * Show exit message to user.
     */
    public String showExit() {
        return showToUser(EXIT_TASK);
    }

    /**
     * Show invalid command message to user.
     *
     * @param message message of invalid command
     */
    public String showInvalid(String message) {
        return showToUser(INVALID_TASK, message);
    }

    /**
     * Show all current tasks to user.
     *
     * @param tasks list of all tasks
     */
    public String showAllTask(TaskList tasks) {
        return showToUser(getAllTaskForView(tasks));
    }

    /**
     * Show all find tasks to user.
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
