package duke;

/**
 * An exception object specifically for Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs an a exception specific to Duke.
     * @param message The message of the exception
     */
    DukeException(String message) {
        super(message);
    }

    /**
     * Prints the exception message when there is an exception.
     * @return The string of the message of the exception.
     */
    public String toString() {
        return "\t" + getMessage();
    }
}
