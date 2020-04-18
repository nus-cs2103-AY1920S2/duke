package duke.exceptions;

/**
 * A Duke exception that warns users when entering invalid indexes.
 */
public class InvalidIndexException extends DukeException {

    String message;

    /**
     * Constructor of this exception.
     * @param message The message to be shown to the user.
     */
    public InvalidIndexException(String message) {
        this.message = message;
    }

    /**
     * A string representation of this exception as a message.
     * @return The message to warn the user.
     */
    public String toString() {
        return this.message;
    }
}