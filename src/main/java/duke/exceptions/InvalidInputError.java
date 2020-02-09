package duke.exceptions;

/**
 * The InvalidInputError program is an error thrown when user inputs an invalid input.
 *
 * @version 1.1
 * @since 9/2/2020
 */
public class InvalidInputError extends Exceptions {

    public InvalidInputError() {

        super(null);
    }

    /**
     * String representation of the exception.
     *
     * @return error message.
     */
    @Override
    public String toString() {

        return "OOPS! I'm sorry, but I don't know what that means.";
    }
}
