package seedu.java.util;

import java.util.Scanner;

/**
 * A UI for user interaction.
 * intro()
 * showLoadingError()
 * inputLoop()
 * Controls how Duke should look like.
 */
public class Ui {
    /**
     * Prints an error. Intended for storage if it fails to load
     */
    public void showLoadingError() {
        System.out.println("Opening with a new blank file");
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