package duke.exceptions;

/**
 * The MissingTaskNumber program is an error thrown when user input is missing required task number.
 *
 * @version 1.1
 * @since 9/2/2020
 */
public class MissingTaskNumberError extends Exceptions {

    /**
     * Constructor.
     */
    public MissingTaskNumberError() {

        super(null);

    }

    /**
     * String representation of the exception.
     *
     * @return error message.
     */
    @Override
    public String toString() {

        return "I'm sorry, the task number is missing.";
    }
}
