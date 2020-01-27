/**
 * Represents the exceptions encountered during
 * execution of the Duke chat-bot.
 */
public class DukeException extends Exception {
    // exception related to duke
    String errorMessage;

    /**
     * Constructor for the exception.
     * @param errorMessage message to be printed.
     */
    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    /**
     * String representation of the exception.
     * @return String representation.
     */
    public String toString() {
        return this.errorMessage;
    }
}
