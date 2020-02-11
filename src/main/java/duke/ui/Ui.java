package duke.ui;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    public Ui() {
    }

    /**
     * Prints a welcome message when Duke is initialised.
     * @return Welcome message.
     */
    public String getWelcome() {
        String greeting = "Hello! I'm Woody and I'm always here to keep you company.\n"
                + "Let me know what you need and I'll be right on it.\n";
        return greeting;
    }

    /**
     * Prints an error message.
     * @param errorMsg Error message.
     * @return Error message to display to user.
     */
    public String getError(String errorMsg) {
        return "OOPS!!! " + errorMsg;
    }

    /**
     * Prints an error message for errors encountered during loading.
     * @return Loading error message.
     */
    public String getLoadingError() {
        return "OOPS!!! Error when loading tasks from file to Woody.";
    }
}
