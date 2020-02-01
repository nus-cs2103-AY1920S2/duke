package duke.exceptions;

/**
 * Custom Exception to be used when a missing description is encountered
 */
public class MissingDescriptionException extends DukeException {
    /**
     * Returns a String representation of the exception
     * @return A String representation of the exception
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! The description cannot be empty.";
    }
}
