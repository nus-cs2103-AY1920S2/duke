package duke.exceptions;

/**
 * Custom Exception to be used when an Unknown Command is encountered
 */
public class UnknownCommandException extends DukeException {
    /**
     * Returns a String representation of the exception
     * @return A String representation of the exception
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
