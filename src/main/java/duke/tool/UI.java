package duke.tool;

import java.util.Scanner;

/**
 * deals with interactions with the user.
 */
public class UI {
    public static String UIString = "";
    private static String indent = "    ";
    private static String line = "    ____________________________________________________________";
    private Storage storage;
    private Scanner userScanner = new Scanner(System.in);

    public UI(Storage storage) {
        this.storage = storage;
    }

    /**
     * UI prints the welcome message.
     */
    public String printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello! I'm Snow\nWhat can I do for you?\n" + logo + "\n";
    }

    public String printLine() {
        UIString += line + "\n";
        return line + "\n";
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
