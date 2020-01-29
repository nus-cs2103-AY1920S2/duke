package duke.exceptions;

/**
 * Exception when to do command has issue.
 */
public class ToDoException extends DukeException {

    /**
     * Constructor for ToDoException.
     *
     * @param message any message to convey.
     */
    public ToDoException(String message) {
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
                + "\nPlease enter another todo command, this time, with "
                + "a known command word/valid number;\n"
                + "For example, 'todo good'\n"
                + ExceptionsConstant.FORMAT_LINE;
    }

}
