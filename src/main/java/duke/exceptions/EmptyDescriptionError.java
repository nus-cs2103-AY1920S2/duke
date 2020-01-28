package duke.exceptions;

/**
 * The EmptyDescriptionError program is an error thrown when user input is missing required description.
 *
 * @version 1.0
 * @since 2020-01-28
 */
public class EmptyDescriptionError extends Exceptions {

    public EmptyDescriptionError(String type) {
        super(type);
    }

    /**
     * Constructs error message with respect the error.
     *
     * @return error message of the error.
     */
    @Override
    public String errorMessage() {

        return "\nOPPS! The description of a " + super.getType() + " cannot be empty";

    }
}
