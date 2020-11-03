package duke.ui;

/**
 * Allows Duke to obtain input from and display output to the user.
 */
public interface Ui {
    /**
     * Displays a greeting to the user.
     */
    void showGreeting();

    /**
     * Displays a reply to the user.
     * 
     * @param reply Reply to be displayed.
     */
    void showReply(String reply);

    /**
     * Displays an error to the user.
     * 
     * @param error Error to be displayed.
     */
    void showError(String error);

    /**
     * Gets user input.
     * 
     * @return User input.
     */
    String getInput();

    /**
     * Closes Ui and performs cleanup operations.
     */
    void shutDown();
}