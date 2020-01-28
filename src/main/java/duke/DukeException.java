package duke;

/**
 * Indicates an exception in the duke application.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the specified detail message.
     * @param message The detail message.
     */
    public DukeException(String message) {
        super(message);
    }
}