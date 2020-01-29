/**
 * DukeException class for custom Exception handling
 */
public class DukeException extends Exception {

    /**
     * Creates a custom Exception handling
     * @param message message that is to be printed when an error occurs.
     */
    public DukeException (String message) {
        super(message);
    }
}
