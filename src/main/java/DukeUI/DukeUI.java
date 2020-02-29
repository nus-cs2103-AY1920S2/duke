package dukeui;

import dukeexceptions.DukeException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a minial UI program that is used to interact with the user.
 * Prints messages as well as obtains input when necessary
 */

public class DukeUI {
    private String currMessage = "";
    private final Scanner IN;
    private final PrintStream OUT;

    public DukeUI() {
        this(System.in, System.out);
    }

    public DukeUI(InputStream in, PrintStream out) {
        this.IN = new Scanner(in);
        this.OUT = out;
    }

    public void holdCurrentMessage(String customMessage) {
        currMessage += customMessage + "\n";
    }

    public String getCurrentMessage() {
        String temp = currMessage;
        currMessage = "";
        return temp;
    }

    /**
     * Returns the welcome message as a String
     *
     * @return String welcome message.
     */
    public String getWelcomeMessage() {
        this.holdCurrentMessage("    Hello I'm Shinobu!");
        this.holdCurrentMessage("    I can help you track the tasks that you need to do!");
        this.holdCurrentMessage("    What can I do for you today?\n");
        this.holdCurrentMessage("    Use 'help' at any time to see the available commands.");

        return this.getCurrentMessage();
    }

    /**
     * Prints out the error message from a DukeException.
     *
     * @param e DukeException
     */
    public String getErrorMessage(DukeException e) {
        return e.getMessage();
    }

    public void holdListOfTasks(ArrayList<String> inputList) {
        for (String curr : inputList) {
            this.holdCurrentMessage(curr);
        }
    }
}