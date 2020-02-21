package duke.exception;

/**
 * Indicates that a user command is invalid
 */
public class InvalidCommandException extends Exception {

    /**
     * Constructor with a message
     * @param msg
     */
    public InvalidCommandException(String msg) {
        super(String.format("'%s' is an invalid command", msg));
    }
}
