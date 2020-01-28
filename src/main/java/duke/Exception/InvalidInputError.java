package duke.Exception;

/**
 * @version 1.0
 * @since 2020-01-28
 */
public class InvalidInputError extends Exceptions {

    public InvalidInputError() {
        super(null);
    }

    /**
     * Constructs error message with respect the error
     *
     * @return error message of the error
     */
    @Override
    public String errorMessage() {
        return "OOPS! I'm sorry, but I don't know what that means.";
    }
}
