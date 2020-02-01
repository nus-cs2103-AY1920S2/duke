package duke.exceptions;

/**
 * Custom Exception to be used when an Invalid Argument is encountered
 */
public class InvalidArgumentException extends DukeException {
    /**
     * Returns a String representation of the exception
     * @return A String representation of the exception
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! That's an invalid argument. Please check your inputs and try again!";
    }
}
