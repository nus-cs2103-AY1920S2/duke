package ui;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DIVIDER = new String(new char[50]).replace('\0', '_');
    private static final String WELCOME_MESSAGE = "Hello there!";
    private static final String EXIT_MESSAGE = "Goodbye, see you again!";
    private static final String INPUT_PROMPT = "Enter your command: ";
    private final Scanner in;

    /**
     * Constructor for our UI component.
     * @param in Method of input.
     */
    public Ui(Scanner in) {
        this.in = in;
    }

    /**
     * Prints a huge Duke logo and greets user.
     */
    public void showWelcome() {
        System.out.println(LOGO);
        System.out.println(WELCOME_MESSAGE);
        showLine();
    }

    /**
     * Prints a divider to for better visuals.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Bids user farewell.
     */
    public void showExit() {
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Gets a command from user.
     * @return String representation of user command.
     */
    public String getUserCommand() {
        System.out.println(INPUT_PROMPT);
        return in.nextLine().toLowerCase();
    }

}
