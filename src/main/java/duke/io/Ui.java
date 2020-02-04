package duke.io;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {

    protected static final String OUTPUT_INDENTATION = "";
    protected static final String OUTPUT_HORIZONTAL_LINE = "\t________________________________________________________";

    protected Scanner scanner;

    /**
     * Constructor of the Ui.
     *
     * @param ioStream An input stream for the Ui object to read inputs from.
     */
    public Ui(InputStream ioStream) {
        scanner = new Scanner(ioStream);
    }

    /**
     * Reads the input stream for a new command.
     *
     * @return A string that contains the input command and its arguments.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the Duke welcome message.
     */
    public void printWelcomeMessage() {
        System.out.println("Hello");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints the Duke goodbye message.
     */
    public void printGoodbyeMessage() {
        System.out.println("Bye. Hope to never see you again!");
    }

    /**
     * Prints one or more lines of error messages.
     *
     * @param error A variable number of strings, each would print a line of error.
     */
    public void printError(String... error) {
        for (int i = 0; i < error.length; ++i) {
            System.out.println(getIndentation(1) + error[i]);
        }
    }

    protected String getIndentation(int numTabs) {
        String ret = "";

        for (int i = 0; i < numTabs; ++i) {
            ret += OUTPUT_INDENTATION;
        }

        return ret;
    }
}
