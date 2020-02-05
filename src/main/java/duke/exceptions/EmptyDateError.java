package duke.exceptions;

/**
 * The EmptyDateError program is an error thrown when user input is missing required date.
 *
 * @version 1.0
 * @since 28/1/2020
 */
public class EmptyDateError extends Exceptions {

    public EmptyDateError(String type) {

        super(type);
    }

    /**
     * Constructs error message with respect the error.
     *
     * @return error message of the error.
     */
    @Override
    public String errorMessage() {

        return "\nOPPS! The date of a " + super.getType() + " cannot be empty.";

    }
}
