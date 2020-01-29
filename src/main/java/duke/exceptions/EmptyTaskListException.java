package duke.exceptions;

/**
 * Exception when the TaskList is empty but an unexpected action is commanded by the user.
 */
public class EmptyTaskListException extends DukeException {

    /**
     * Constructor for EmptyTaskListException.
     *
     * @param message any message to convey.
     */
    public EmptyTaskListException(String message) {
        super(message);
    }

    /**
     * Provides a custom String representation of the current Exception, which includes formatting lines.
     *
     * @return the presentable String representation.
     */
    @Override
    public String toString() {
        return ExceptionsConstant.FORMAT_LINE
                + "\nNo tasks! You're good to go!\nPlease exit using 'bye' command. :)\n"
                + ExceptionsConstant.FORMAT_LINE;
    }


}
