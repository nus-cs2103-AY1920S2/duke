package duke.util;

import duke.exception.DukeException;

import java.util.Scanner;

/*
 * Ui
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 28 Jan 2020
 *
 */

/**
 * Ui class defines the behavior of the class that
 * handles the user interface related matters.
 * @author Mario Lorenzo
 */

public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui instance.
     */

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message.
     */

    public void start() {
        printLine();
        System.out.println("Hello! I'm Duke.\nAdd your command here.");
        printLine();
    }

    /**
     * Prints the error message.
     * @param e The error that is being raised.
     */
    public void displayErrorMessage(DukeException e) {
        System.err.println(e);
    }

    /**
     * Prints the message.
     * @param message The message.
     */

    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints a line.
     */

    public void printLine() {
        System.out.println("------------------------");
    }

    /**
     * Prints a closing message to the client.
     */

    public void close() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * The readCommand method abstracts the input method
     * done by the Scanner object. It reads the whole line
     * of input entered by the client.
     * @return The String object entered by the client.
     */

    public String readCommand() {
        return this.scanner.nextLine();
    }
}
