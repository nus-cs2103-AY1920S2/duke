/**
 * Deals with interactions with the user.
 */
public class Ui {
    public Ui() {
    }

    /**
     * Prints a welcome message when Duke is initialised.
     */
    public String getWelcome() {
        String greeting = "Hello! I'm Woody and I'm always here to keep you company.\n"
                + "Let me know what you need and I'll be right on it.\n";
        return greeting;
    }

    /**
     * Prints an error message.
     * @param errorMsg Error message.
     */
    public String getError(String errorMsg) {
        return "OOPS!!! " + errorMsg;
    }

    /**
     * Prints an error message for errors encountered during loading.
     */
    public String getLoadingError() {
        return "OOPS!!! Error when loading tasks from file to Woody.";
    }
}
