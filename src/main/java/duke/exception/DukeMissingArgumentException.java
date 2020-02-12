package duke.exception;

/**
 * Thrown when an error occurs due to missing arguments.
 */
public class DukeMissingArgumentException extends DukeException {

    /**
     * Constructs a DukeMissingArgumentException with the specified detail message.
     *
     * @param message The specified detail message.
     */
    public DukeMissingArgumentException(String message) {
        super(message);
    }
}
