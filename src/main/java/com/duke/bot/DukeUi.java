package com.duke.bot;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

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

    public void print(String output) {
        out.println(output);
    }

    public void printEmptyLine() {
        out.println();
    }

    public void resetScanner() {
        in.reset();
    }

    public void setToken(Pattern pattern) {
        in.useDelimiter(pattern);
    }

    public void setToken(String pattern) {
        in.useDelimiter(pattern);
    }

    public void printError(String message) {
        System.err.println(message);
    }

    public String getNext() {
        return in.next();
    }

    public int getNextInt() {
        return in.nextInt();
    }

    public String getNextLine() {
        return in.nextLine();
    }
}
