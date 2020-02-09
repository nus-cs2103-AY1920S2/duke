package duke.exceptions;

/**
 * The EmptyDateError program is an error thrown when user input is missing required date.
 *
 * @version 1.1
 * @since 9/2/2020
 */
public class EmptyDateError extends Exceptions {

    public EmptyDateError(String type) {

        super(type);
    }

    /**
     * String representation of the exception.
     *
     * @return error message.
     */
    @Override
    public String toString() {
        return "OPPS! The date of a " + super.getType() + " cannot be empty.";
    }
}
