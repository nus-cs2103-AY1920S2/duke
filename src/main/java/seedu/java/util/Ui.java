package seedu.java.util;

import java.util.Scanner;

/**
 * A UI for user interaction.
 * intro()
 * showLoadingError()
 * inputLoop()
 */
public class Ui {
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints the logo.
     */
    public void intro() {
        System.out.println(logo + "\n"
                + "     Oi What you waaaaaant?\n"
                + "    _________________________________");
    }

    public void showLoadingError() {
        System.out.println("System failed to load file. Opening with a new blank file");
    }

    /**
     * Takes in an input and returns the input. Intended for TaskList & parsing.
     * @return input for parsing
     */
    public String input() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    public void output(String out) {
        System.out.println(out);
    }
}