package duke.ui;

import duke.exception.DukeException;

import java.util.Scanner;

/**
 * Represents the Ui element.
 * This is a class to handle input and output, allowing interactions with the user.
 */
public class Ui {
    /** The divider of the output message. */
    private String divider = "____________________________________________________________";
    /** Buffer for message to be printed. */
    private String messageBuffer = "";
    /** The scanner to get input from the user. */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Construct a new Ui object with desired welcome and exit messages.
     */
    public Ui() {
        this.messageBuffer = "";
        this.scanner = new Scanner(System.in);
    }

    /**
     * Get user input from System.in.
     *
     * @return the next line of the user input from System.in.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Adds message to the message buffer.
     *
     * @param message message to be added to buffer.
     */
    public void addMessage(String message) {
        messageBuffer += message;
    }

    /**
     * Prints desired message in buffer with dividers.
     * Buffer is cleared after this method.
     */
    public void printBufferMessage() {
        printDividerLine();
        System.out.print(messageBuffer);
        printDividerLine();
        clearBuffer();
    }

    /**
     * Prints desired message with dividers.
     *
     * @param message Message to be printed
     */
    public void printMessage(String message) {
        printDividerLine();
        System.out.print(message);
        printDividerLine();
    }

    /**
     * Gets the message in buffer.
     *
     * @return message in buffer.
     */
    public String getMessageBuffer() {
        return messageBuffer;
    }

    /**
     * Clears the message buffer.
     */
    public void clearBuffer() {
        messageBuffer = "";
    }

    /**
     * Prints the exception message.
     *
     * @param e Exception to be printed.
     */
    public void printException(DukeException e) {
        printDividerLine();
        System.out.println(e.getMessage());
        printDividerLine();
    }

    /**
     * Prints the divider line.
     */
    public void printDividerLine() {
        System.out.println(divider);
    }
}
