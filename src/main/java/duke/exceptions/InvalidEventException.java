package duke.exceptions;

/**
 * A Duke exception that warns users that the event format is invalid.
 */
public class InvalidEventException extends DukeException {

    String message;

    /**
     * A constructor that warns the user with a mesage.
     * @param message The string to be shown.
     */
    public InvalidEventException(String message) {
        this.message = message;
    }

    /**
     * A string representation of this exception with the message.
     * @return The message to warn user.
     */
    public String toString() {
        return this.message;
    }
}