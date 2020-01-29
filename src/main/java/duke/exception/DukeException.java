package duke.exception;

public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     * Create a new DukeException with the input error message.
     *
     * @param message The message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Throws a DukeException if the input condition is true.
     *
     * @param condition The condition to test.
     * @param message The message to create the DukeException with, if the condition is true.
     * @throws DukeException If the condition is true.
     */
    public static void throwIf(boolean condition, String message) throws DukeException {
        if (condition) {
            throw new DukeException(message);
        }
    }
}
