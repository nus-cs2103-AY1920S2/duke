//package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A class interacts with users from the text ui.
 * Includes methods to read input, print normal message, print error message, etc.
 */
public class Ui {
    protected String userName;
    protected BufferedReader br;

    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String line = "____________________________________________________________\n";
    static String greeting = "I'm Duke.\n What can I do for you?\n";

    /**
     * Constructs an {@code Ui} with a buffered reader.
     */
    public Ui() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Set the user name.
     * @param userName A valid user name.
     */
    public void setName(String userName) {
        this.userName = userName;
    }

    /**
     * Prints the response from command execution to the screen.
     * @param result A response from command execution as string.
     */
    public void printCommandResult(String result) {
        System.out.println(line + result+ line);
    }

    /**
     * Prints the error message to the screen.
     * @param errorMessage The message of an raised exception.
     */
    public void printErrorMessage(String errorMessage) {
        System.out.println(line + errorMessage + line);
    }

    /**
     * Prints message to aks for user name and set this.userName as the inputted name.
     */
    public void askForName() {
        this.printCommandResult("Please input user name.\n");
        String userName = this.getUserInput();
        this.setName(userName);
    }

    /**
     * Print message to greet the user.
     */
    public void greet() {
        this.printCommandResult("Hello "+ this.userName + "! " + greeting);
    }

    /**
     * Read user input to the program.
     * @return next line of input.
     */
    public String getUserInput() {
        try {
            return this.br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
