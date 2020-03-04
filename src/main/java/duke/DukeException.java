package duke;

/**
 * {@code DukeException} is the superclass of those exceptions that can be thrown during the normal operation of
 * {@code Duke}.
 */
public class DukeException extends Exception {
    /**
     * Constructs a {@code DukeException} with the specified detail message.
     *
     * @param message the detail message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Constructs a {@code DukeException} with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }
}
