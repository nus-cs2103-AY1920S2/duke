package duke.exceptions;

/**
 * Exception when delete command has an issue.
 */
public class DeleteException extends DukeException {

    /**
     * Constructor for DeleteException.
     *
     * @param message any message to convey.
     */
    public DeleteException(String message) {
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
                + "\nDeleting Tasks require a valid task number and the task needs to be previously entered\n"
                + ExceptionsConstant.FORMAT_LINE;
    }
}
