package duke.exceptions;

/**
 * The EmptyDescriptionError program is an error thrown when user input is missing required description.
 *
 * @version 1.2
 * @since 19/2/2020
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

        if (super.type.equals("T")) {

            return "OPPS! The description of a todo task cannot be empty";

        } else if (super.type.equals("D")) {

            return "OPPS! The description of a deadline task cannot be empty";

        }

        assert super.type.equals("E") : "Wrong task type!";

        return "OPPS! The description of a event task cannot be empty";
    }
}
