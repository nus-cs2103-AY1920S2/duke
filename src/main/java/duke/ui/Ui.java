package duke.ui;

/**
 * Allows <code>Duke</code> to display output to the user.
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
}