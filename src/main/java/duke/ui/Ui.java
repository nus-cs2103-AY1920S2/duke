package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.Scanner;

/**
 * Represents the Ui element.
 * This is a class to handle input and output, allowing interactions with the user.
 */
public class Ui {
    /** The divider of the output message. */
    private String divider = "    ____________________________________________________________";
    /** The welcome message. */
    private String welcomeMessage = "";
    /** The exit message. */
    private String exitMessage = "";
    /** The scanner to get input from the user. */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Construct a new Ui object with desired welcome and exit messages.
     *
     * @param welcomeMessage welcome message to be shown when program starts.
     * @param exitMessage exit message to be shown when program exits.
     */
    public Ui(String welcomeMessage, String exitMessage) {
        this.welcomeMessage = welcomeMessage;
        this.exitMessage = exitMessage;
        Scanner scanner = new Scanner(System.in);
    }

    /**
     * Get user input from System.in.
     *
     * @return the next line of the user input from System.in.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Prints welcome message.
     */
    public void printWelcomeMessage() {
        printDividerLine();
        System.out.println(welcomeMessage);
        printDividerLine();
    }

    /**
     * Prints exit message.
     */
    public void printExitMessage() {
        printDividerLine();
        System.out.println(exitMessage);
        printDividerLine();
    }

    /**
     * Prints desired message with dividers.
     * Note that that message string should have newline character at the end.
     */
    public void printMessage(String message) {
        printDividerLine();
        System.out.print(message);
        printDividerLine();
    }

    /**
     * Prints desired message without dividers.
     * Note that that message string should have newline character at the end.
     */
    public void printMessageNoDivider(String message) {
        System.out.print(message);
    }

    /**
     * Prints the String representation of a Task.
     */
    public void printTask(Task task) {
        System.out.println(task);
    }

    /**
     * Prints the exception message.
     */
    public void printException(DukeException e) {
        printDividerLine();
        System.out.println(e.getMessage());
        printDividerLine();
    }

    /**
     * Prints the divider line.
     */
    public void printDividerLine() {
        System.out.println(divider);
    }
}
