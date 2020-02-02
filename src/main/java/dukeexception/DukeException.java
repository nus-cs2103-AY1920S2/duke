package dukeexception;

/**
 * Creates a new Exception when thrown.
 */
public class DukeException extends Exception {

    /**
     * Empty constructor for DukeException.
     */
    public DukeException() {
    }

    /**
     * Constructor for DukeException.
     * @param msg The message to be printed when Exception is thrown.
     */
    public DukeException(String msg) {
        super(msg);
    }

}
