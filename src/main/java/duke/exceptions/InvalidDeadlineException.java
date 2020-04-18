package duke.exceptions;

/**
 * A Duke exception to warn users that deadline format are invalid.
 */
public class InvalidDeadlineException extends DukeException {

    String message;

    /**
     * Constructor that warns the user with a message.
     * @param message The string to be shown.
     */
    public InvalidDeadlineException(String message) {
        this.message = message;
    }

    /**
     * A string representation of this exception with the message.
     * @return The message to warn users.
     */
    public String toString() {
        return this.message;
    }
}