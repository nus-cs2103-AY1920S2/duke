package com.duke.bot.component;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Represents the UI that handles the interaction between users and Duke Bot.
 */
public class DukeUi {
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon! :)";
    public static final String GREET_MESSAGE = "Hello! I'm duke.Duke\n"
            + "By default, your list of tasks will be saved to \"tasks.txt\".\n"
            + "What can I do for you?\n\n";

    private Scanner in;
    private PrintStream out;

    /**
     * Constructs a DukeUi object.
     *
     * @param in Source of user input.
     * @param out Destination of output stream.
     */
    public DukeUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public DukeUi(String input) {
        this.in = new Scanner(input);
        this.out = System.out;
    }

    /**
     * Prints a message that greets the user when DukeBot starts.
     */
    public static String greet() {
        return "Hello! I'm duke.Duke\n"
                + "By default, your list of tasks will be saved to \"data/tasks.txt\"\n"
                + "What can I do for you?\n\n";
    }


    /**
     * Prints a message that says goodbye to the user when the user closes Duke Bot.
     */
    public void printByeMsg() {
        System.out.println("Bye. Hope to see you again soon! :)");
        out.print(BYE_MESSAGE);
    }

    /**
     * Prints a string onto the output.
     * @param output The String being printed.
     */
    public void print(String output) {
        out.println(output);
    }

    /**
     * Prints an empty line onto the output.
     */
    public void printEmptyLine() {
        out.println();
    }

    /**
     * Resets the properties of the input to default settings.
     */
    public void resetScanner() {
        in.reset();
    }

    /**
     * Set the delimiter when reading in data.
     *
     * @param pattern The regex delimiter pattern used.
     */
    public void setToken(String pattern) {
        in.useDelimiter(pattern);
    }

    /**
     * Gets the next output in the input stream.
     *
     * @return The next string in the input stream.
     */
    public String getNext() {
        return in.next();
    }

    /**
     * Gets the next integer in the input stream.
     *
     * @return The next integer in the input stream.
     */
    public int getNextInt() {
        return in.nextInt();
    }

    /**
     * Gets the next string in the input stream, delimited by a newline character.
     *
     * @return The next line in the input stream.
     */
    public String getNextLine() {
        return in.nextLine();
    }
}
