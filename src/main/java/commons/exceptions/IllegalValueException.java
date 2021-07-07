package commons.exceptions;

/**
 * Signals that some given data does not fulfill some constraints.
 */
public class IllegalValueException extends Exception {

    /**
     * Creates new illegal value exception.
     *
     * @param message should contain relevant information on the failed constraint(s)
     */
    public IllegalValueException(String message) {
        super(message);
    }

    /**
     * Creates new illegal value exception including cause.
     *
     * @param message should contain relevant information on the failed constraint(s)
     * @param cause   of the main exception
     */
    public IllegalValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
