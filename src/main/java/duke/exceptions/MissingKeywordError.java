package duke.exceptions;

/**
 * The MissingKeyword program is an error thrown when user input is missing required keyword.
 *
 * @version 1.0
 * @since 2020-01-28
 */
public class MissingKeywordError extends Exceptions {

    /**
     * Constructor.
     */
    public MissingKeywordError() {
        super(null);
    }

    /**
     * Constructs error message with respect the error.
     *
     * @return error message of the error.
     */
    @Override
    public String errorMessage() {
        return "\nOOPS! I'm sorry, the keyword is missing.";
    }
}
