package core;

import java.util.Scanner;

/**
 * Platform to interact with the user.
 */
public class Ui {
    private String response = "";
    private Scanner sc;

    /**
     * Constructor to initialize the scanner.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Gets the user input.
     *
     * @return the user input in string.
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Displays the welcome message to the user.
     */
    public void preLog() {
        display(UiMessage.GREETING.getMsg());
    }

    /**
     * Displays the exiting message to the user.
     */
    public void endLog() {
        display(UiMessage.FAREWELL.getMsg());
    }

    /**
     * Displays the error message to the user.
     *
     * @param error is the error message.
     */
    public void errorLog(String error) {
        display(error);
    }

    /**
     * Displays text to the user under the standard format.
     *
     * @param msg is the text to be displayed.
     */
    public void display(String... msg) {
        response = "";
        for (String str : msg) {
            response += str + "\n";
        }
    }

    public String getResponse() {
        assert !response.isEmpty();
        return response;
    }

}
