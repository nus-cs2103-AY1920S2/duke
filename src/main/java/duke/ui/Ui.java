package duke.ui;

import java.util.Scanner;

/**
 * Interacts with the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs a new Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
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
        return showToUser("Hello! I'm Duke", "What can I do for you?");
    }

    /**
     * Returns the exit message when the duke application is terminated.
     *
     * @return The exit message when the duke application is terminated.
     */
    public String showExit() {
        sc.close();
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a string representation of the message to be printed.
     *
     * @param message The message to be printed.
     * @return A string representation of the message to be printed.
     */
    public static String showToUser(String... message) {
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
