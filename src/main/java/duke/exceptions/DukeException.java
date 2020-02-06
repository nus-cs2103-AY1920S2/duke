package duke.exceptions;

/**
 * Throws exception specifically to duke.
 */
public class DukeException extends Exception {

    /**
     * Creates a new DukeException containing a specific error message.
     *
     * @param message the error message to be printed out
     */
    public DukeException(String message) {
        super(message);
        assert message != null : "No error message to print out";
    }
}
