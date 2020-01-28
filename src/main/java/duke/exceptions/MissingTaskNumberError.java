package duke.exceptions;

/**
 * The MissingTaskNumber program is an error thrown when user input is missing required task number.
 *
 * @version 1.0
 * @since 2020-01-28
 */
public class MissingTaskNumberError extends Exceptions {

    public MissingTaskNumberError() {
        super(null);
    }

    /**
     * Constructs error message with respect the error.
     *
     * @return error message of the error.
     */
    @Override
    public String errorMessage() {
        return "\nOOPS! I'm sorry, the task number is missing.";
    }
}
