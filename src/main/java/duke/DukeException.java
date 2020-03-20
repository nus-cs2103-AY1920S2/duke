package duke;

/**
 * Represents any error encountered within Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new custom exception encountered within Duke.
     *
     * @param message the message of the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
