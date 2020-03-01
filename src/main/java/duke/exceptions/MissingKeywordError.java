package duke.exceptions;

/**
 * The MissingKeyword program is an error thrown when user input is missing required keyword.
 *
 * @version 1.1
 * @since 9/2/2020
 */
public class MissingKeywordError extends Exceptions {

    /**
     * Constructor.
     */
    public MissingKeywordError() {

        super(null);

    }


    /**
     * String representation of the exception.
     *
     * @return error message.
     */
    @Override
    public String toString() {

        return "I'm sorry, the keyword is missing.";
    }
}
