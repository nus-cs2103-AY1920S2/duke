package duke.exceptions;

/**
 * Custom Exception to be used when there is a Missing Parameter for the Deadline Command is encountered
 */
public class MissingDeadlineParamException extends DukeException {
    /**
     * Returns a String representation of the exception
     * @return A String representation of the exception
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! Remember to use \"/by\" for Deadlines.";
    }
}
