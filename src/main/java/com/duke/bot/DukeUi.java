package com.duke.bot;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents the UI that handles the interaction between users and Duke Bot.
 */
public class DukeUi {
    private Scanner in;
    private PrintStream out;

    /**
     * Constructs a DukeUi object.
     * @param in Source of user input.
     * @param out Destination of output stream.
     */
    public DukeUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints a message that greets the user when DukeBot starts.
     */
    public void greet() {
        out.println("Hello! I'm duke.Duke");
        out.println("By default, your list of tasks will be saved to \"tasks.txt\".");
        out.println("What can I do for you?");
        out.println();
    }


    /**
     * Prints a message that says goodbye to the user when the user closes Duke Bot.
     */
    public void printByeMsg() {
        System.out.println("Bye. Hope to see you again soon! :)");
    }

}
