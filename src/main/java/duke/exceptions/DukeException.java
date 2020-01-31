package duke.exceptions;

/**
 * throws exception specifically to duke.
 */
public class DukeException extends Exception {

    /**
     * creates a new DukeException containing a specific error message.
     * @param message the error message to be printed out
     */
    public DukeException(String message) {
        super(message);
    }
}
