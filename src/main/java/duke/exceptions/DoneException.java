package duke.exceptions;

/**
 * Exception when done command has an issue.
 */
public class DoneException extends DukeException {

    /**
     * Constructor for DeadlineException.
     *
     * @param message any message to convey.
     */
    public DoneException(String message) {
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
                + "\nSeems like you are kinda tired. Please remember to define a Task Number!\n"
                + "Or, you could also take a break. :)\n"
                + ExceptionsConstant.FORMAT_LINE;
    }

}
