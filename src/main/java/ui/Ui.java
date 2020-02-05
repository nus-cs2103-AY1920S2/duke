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
    public String getWelcomeMessage() {
        return LOGO + "\n" + WELCOME_MESSAGE;
    }

    /**
     * Prints a divider to for better visuals.
     */
    public String getLine() {
        return DIVIDER;
    }

    /**
     * Bids user farewell.
     */
    public String getExitMessage() {
        return EXIT_MESSAGE;
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
