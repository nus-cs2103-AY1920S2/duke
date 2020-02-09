package duke.exceptions;

/**
 * The InvalidDateError program is an error thrown when user inputs an invalid date format.
 *
 * @version 1.1
 * @since 9/2/2020
 */
public class InvalidDateError extends Exceptions {

    public InvalidDateError(String type) {

        super(type);
    }


    /**
     * String representation of the exception.
     *
     * @return error message.
     */
    @Override
    public String toString() {

        if (type.equals("DEADLINE")) {

            return "Date should be in d/MM/yyyy HH:mm format";

        } else {

            return "Date should be in d/MM/yyyy HH:mm to /MM/yyyy HH:mm format";

        }
    }
}
