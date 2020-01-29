package duke.io;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {

    protected static final String OUTPUT_INDENTATION = "\t";
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
        stylizedPrint(
                "Hello",
                "What can I do for you?"
        );
    }

    /**
     * Prints the Duke goodbye message.
     */
    public void printGoodbyeMessage() {
        stylizedPrint("Bye. Hope to never see you again!");
    }

    /**
     * Prints one or more lines of error messages.
     *
     * @param error A variable number of strings, each would print a line of error.
     */
    public void printError(String... error) {
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        for (int i = 0; i < error.length; ++i) {
            System.out.println(getIndentation(1) + error[i]);
        }
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    /**
     * Prints a horizontal bar.
     */
    public void printHorizontalBar() {
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    /**
     * Prints one or more lines of text with ASCII accents.
     *
     * @param lines A variable number of strings, each would print a line of text.
     */
    public void stylizedPrint(String... lines) {
        stylizedPrint(1, lines);
    }

    /**
     * Prints one or more lines of text with ASCII accents.
     *
     * @param numTabs The number of tab indentations for the output text.
     * @param lines A variable number of strings, each would print a line of text.
     */
    public void stylizedPrint(int numTabs, String... lines) {
        numTabs = Math.max(0, numTabs);
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        for (int i = 0; i < lines.length; ++i) {
            System.out.println(getIndentation(numTabs) + lines[i]);
        }
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    protected String getIndentation(int numTabs) {
        String ret = "";

        for (int i = 0; i < numTabs; ++i) {
            ret += OUTPUT_INDENTATION;
        }

        return ret;
    }
}
