package duke.exception;

/**
 * Extends from the java <code>Exception</code> class and represents exceptions happened in the Duke chat bot.
 */
public class DukeException extends Exception {
    /**
     * Constructs a duke exception instance.
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
