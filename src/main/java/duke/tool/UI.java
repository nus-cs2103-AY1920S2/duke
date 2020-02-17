package duke.tool;

import java.util.Scanner;

/**
 * deals with interactions with the user.
 */
public class UI {
    public static String UIString = "";

    /**
     * UI prints the welcome message.
     */
    public String printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello! I'm Snow\nWhat can I do for you?\n" + "\n";
    }


    public String print(String message) {
        UIString += message + "\n";
        return message + "\n";
    }

    /**
     * Prints the error message.
     *
     * @param message The message String to be printed
     */
    public String printError(String message) {
        UIString += message + "\n";
        return message + "\n";
    }
}
