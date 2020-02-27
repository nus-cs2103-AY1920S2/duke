package duke.util;

import java.util.Scanner;

/**
 * Represents the user interface of the duke chat bot.
 */
public class Ui {
    private static final String SEPERARION_LINE = "_".repeat(50) + "\n";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Formats the given message by adding two separation lines at the top and the bottom.
     * @param message The message to be formatted.
     * @return The formatted message.
     */
    public String formatMessage(String message) {
        return SEPERARION_LINE + message + SEPERARION_LINE;
    }

    /**
     * Displays the message to the user in a certain format.
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(formatMessage(message));
    }

    /**
     * Shows the welcome message on start up.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetings = "Hello! I'm\n" + logo + "\nWhat can I do for you?\n";
        showMessage(greetings);
    }

    public void showFarewell() {
        String farewell = "Hope to see you next time! xD\n";
        showMessage(farewell);
    }

    public void showLoadingError() {
        String message = "Failed to load the existing tasks... can you check if the file is still there? :p\n";
        showMessage(message);
    }

    public void showError(String message) {
        showMessage(message);
    }

    /**
     * Reads the user input.
     * @return A string representation of the user input.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }
}
