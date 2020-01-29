package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

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

    private void showToUser(String... message) {
        for (String m : message) {
            System.out.println(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX));
        }
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        showToUser(WELCOME_MESSAGE);
    }

    public void showLoadingError() {
        showToUser(LOADING_ERROR);
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showError(DukeException ex) {
        showToUser(ex.getMessage());
    }

    public void showAddTask(Task task, int totalTasks) {
        showToUser(ADD_TASK, task.toString(), String.format(NUM_OF_TASKS, totalTasks));
    }

    public void showDeleteTask(Task task, int totalTasks) {
        showToUser(DELETE_TASK, task.toString(), String.format(NUM_OF_TASKS, totalTasks));
    }

    public void showDone(Task task) {
        showToUser(DONE_TASK, task.toString());
    }

    public void showExit() {
        showToUser(EXIT_TASK);
    }

    public void showInvalid(String message) {
        showToUser(INVALID_TASK, message);
    }

    public void showAllTask(TaskList tasks) {
        showToUser(getAllTaskForView(tasks));
    }

    public void showFindTask(TaskList tasks) {
        showToUser(FIND_TASK, getAllTaskForView(tasks));
    }

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
