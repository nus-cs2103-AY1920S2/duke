/**
 * Ui class handles user input.
 */
public class Ui {

    /**
     * Returns loading error where file is not loaded.
     */
    public String showLoadingError() {
        return "     ☹ OOPS!!! File not loaded.";
    }

    /**
     * Returns a string containing error.
     * @param error error message.
     */
    public String showError(String error) {
        return "     ☹ OOPS!!! " + error;
    }
}