package ui;

import java.util.Scanner;

/**
 * This class deals with interactions with the user.
 */
public class Ui {

    public static final String LINE = "____________________________________________________________";
    private Scanner sc;

    /**
     * Constructor for creating new Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }


    /**
     * This method prints the chat bot's welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE + "\n Hello from\n" + logo + "\n How can I help you today? \n" + LINE);
    }

    /**
     * Returns the command the user input into chat bot.
     *
     * @return the command input by user.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints the error message when not able to load file.
     */
    public void showLoadingError() {
        System.out.println("ERROR: Not able to load file.");
    }

    /**
     * Prints the error message.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints the horizontal line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints the message to be shown to user.
     */
    public void printMsg(String msg) {
        assert msg.length() != 0 : "Assert error: no message to print!";
        System.out.println(msg);
    }


}
