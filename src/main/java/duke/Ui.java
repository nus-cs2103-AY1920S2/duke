package duke;

/**
 * Represents a manager for user interaction related functions.
 */
public class Ui {
    /**
     * Constructs a fresh Ui instance.
     */
    public Ui() {
    }

    /**
     * Prints the introductory message for Duke.
     */
    public void printIntro() {
        printLines("Hello! :) I'm Duke.\n" + "     How can I help you today?");
    }

    /**
     * Prints the goodbye message for Duke.
     */
    public void printGoodbye() {
        printLines("Goodbye. See you again soon!");
    }

    /**
     * Prints the divider lines for messages in Duke.
     * @param content The content within the divider lines.
     */
    public static void printLines(String content) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + content);
        System.out.println("    ____________________________________________________________");
    }
}