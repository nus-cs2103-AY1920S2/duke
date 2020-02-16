package duke.exceptions;

/**
 * Custom Exception to be used when an Invalid Argument is encountered
 */
public class InvalidDateTimeFormatException extends DukeException {
    /**
     * Returns a String representation of the exception
     * @return A String representation of the exception
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! That's an invalid format for date and time. Please use the following format: 2/12/2019 2000!";
    }
}
