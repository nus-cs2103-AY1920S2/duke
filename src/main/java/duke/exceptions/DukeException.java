package duke.exceptions;

/**
 * The most general Exception in the Duke Program, of which other Exceptions are child classes of.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException, parent exception of all exceptions in Duke program.
     *
     * @param message any message to convey.
     */
    public DukeException(String message) {
        super(message);
    }

}
