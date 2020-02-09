package duke.exceptions;

/**
 * The EmptyDescriptionError program is an error thrown when user input is missing required description.
 *
 * @version 1.1
 * @since 9/2/2020
 */
public class EmptyDescriptionError extends Exceptions {

    public EmptyDescriptionError(String type) {

        super(type);
    }

    /**
     * String representation of the exception.
     *
     * @return error message.
     */
    @Override
    public String toString() {

        return "OPPS! The description of a " + super.getType() + " cannot be empty";
    }
}
