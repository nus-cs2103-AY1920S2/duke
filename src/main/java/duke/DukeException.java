package duke;

/**
 * Customized exception to interact with user.
 */
public class DukeException extends Exception {

    /**
     * DukeException constructor.
     *
     * @param message What to inform the user.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Retrieve the exception message.
     *
     * @return Exception message to be shown to the user.
     */
    @Override
    public String toString() {
        return getMessage();
    }
}
