package duke.ui;

import java.util.Scanner;

public class Ui {
    private final String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
    + "| |_| | |_| |   <  __/\n" + "|____/ \\__._|_|\\_\\___|\n";
    private final String logoDivider = "++++++++++++++++++++++\n";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Print out the welcome message.
     */
    public void showWelcome() {
        System.out.println(logoDivider + logo + "\n" + logoDivider);
        this.dukePrompt(new String[]{"Hey boss! Duke here, at your service.", "What do you need me to do?"});
        System.out.println();
    }

    /**
     * Print out a formatted single line prompt with Duke's label.
     *
     * @param prompt A single string message.
     */
    public void dukePrompt(String prompt) {
        System.out.println("Duke: " + prompt);
    }

    /**
     * Print out a formatted multi line prompt with Duke's label.
     *
     * @param prompts An array of multi line string prompts.
     */
    public void dukePrompt(String[] prompts) {
        for (int i = 0; i < prompts.length; i++) {
            if (i == 0) {
                System.out.println("Duke: " + prompts[i]);
            } else {
                System.out.println("      " + prompts[i]);
            }
        }
    }

    /**
     * Read the user's input command with the correct formatting.
     *
     * @return String of the user's input.
     */
    public String readUserInput() {
        System.out.printf("\nYou:  ");
        String line = scanner.nextLine();
        System.out.println();
        return line;
    }

    /**
     * Prints the error message in the correct formatting with Duke's label.
     *
     * @param errorMessage
     */
    public void showError(String errorMessage) {
        dukePrompt(errorMessage);
    }
}