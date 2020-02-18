package duke.exceptions;

/**
 * The EmptyDateError program is an error thrown when user input is missing required date.
 *
 * @version 1.2
 * @since 19/2/2020
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

        if (super.type.equals("D")) {

            return "OPPS! The date of a deadline task cannot be empty.";

        }

        assert super.type.equals("E") : "Wrong task type!";

        return "OPPS! The date of a event task cannot be empty.";
    }
}
