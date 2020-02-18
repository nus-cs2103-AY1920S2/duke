package duke.exception;

/**
 * Thrown when any error occurs in Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified detail message.
     *
     * @param errorMessage The specified detail error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
