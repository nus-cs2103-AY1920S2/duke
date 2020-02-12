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
    private static final String WELCOME_MESSAGE = "A long time ago in a galaxy far, far away...";
    private static final String EXIT_MESSAGE = "As you wish my lord";
    private static final String HELP_MESSAGE = "Available commands are :\n"
            + "todo (task description)\n" + "event (task description) /at (place)\n"
            + "deadline (task description) /by (date: yyyy-MM-dd HH:mm)\n"
            + "list\n" + "done (number as shown in list)\n" + "delete (number as shown in list)"
            + "undo";

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
        return LOGO + "\n" + DIVIDER + "\n\n\n" + WELCOME_MESSAGE;
    }

    /**
     * Bids user farewell.
     */
    public String getExitMessage() {
        return EXIT_MESSAGE;
    }

    /**
     * Returns a representation of all possible commands.
     * @return String of commands.
     */
    public String getHelpMessage() {
        return HELP_MESSAGE;
    }
}
