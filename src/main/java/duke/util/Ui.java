package duke.util;

import duke.exception.DukeException;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        printLine();
        System.out.println("Hello! I'm Duke.\nAdd your command here.");
        printLine();
    }

    public void displayErrorMessage(DukeException e) {
        System.err.println(e);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void printLine() {
        System.out.println("------------------------");
    }

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
