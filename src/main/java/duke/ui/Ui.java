package duke.ui;

import duke.TaskList;

import java.util.Scanner;

/**
 * Interacts with the user.
 */
public class Ui {
    public static final String INDENT = "  ";
    public static final String MESSAGE_WELCOME = "Hello! I'm Duke. \nWhat can I do for you?";
    public static final String MESSAGE_ADD = "Got it. I've added this task:";
    public static final String MESSAGE_DONE = "Nice! I've marked this task as done: ";
    public static final String MESSAGE_DELETE = "Noted. I've removed this task: ";
    public static final String MESSAGE_FIND = "Here are the matching tasks in your list:";
    public static final String MESSAGE_NO_MATCHING_TASK = "There are no matching tasks in your list.";
    public static final String MESSAGE_LIST = "Here are the tasks in your list:";
    public static final String MESSAGE_NO_TASK = "There are no tasks in the list.";
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    private Scanner sc;

    /**
     * Constructs a new Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns a string message with the number of tasks in the TaskList.
     * @param tasks The TaskList of tasks.
     * @return A string message with the number of tasks in the TaskList.
     */
    public static String getNumberOfTasksMessage(TaskList tasks) {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Returns the user input read.
     * @return The user input read.
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Prints the welcome message when the duke application starts up.
     *
     * @return The welcome message when the duke application starts up.
     */
    public static String showWelcome() {
        return MESSAGE_WELCOME;
    }

    /**
     * Returns the exit message when the duke application is terminated.
     *
     * @return The exit message when the duke application is terminated.
     */
    public String showExit() {
        sc.close();
        return MESSAGE_EXIT;
    }

    /**
     * Returns a string representation of the message to be printed.
     *
     * @param message The message to be printed.
     * @return A string representation of the message to be printed.
     */
    public String showToUser(String... message) {
        return String.join(System.lineSeparator(), message);
    }

    /**
     * Returns the error message when an exception is thrown.
     *
     * @param message The error message.
     * @return The error message when an exception is thrown.
     */
    public String showError(String message) {
        return "OOPS!!! " + message;
    }

    /**
     * Prints the message.
     *
     * @param message The message to be printed.
     */
    public void print(String message) {
        System.out.println(message);
    }
}
