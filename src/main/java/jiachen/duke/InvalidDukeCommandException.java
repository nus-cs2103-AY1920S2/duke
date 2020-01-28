package jiachen.duke;

/**
 * Throws when user tries to use an invalid command
 */
public class InvalidDukeCommandException extends DukeException {
    /**
     * Instantiates a new Invalid duke command exception.
     */
    public InvalidDukeCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
