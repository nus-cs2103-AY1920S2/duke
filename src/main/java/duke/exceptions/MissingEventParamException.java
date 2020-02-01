package duke.exceptions;

/**
 * Custom Exception to be used when there is a Missing Parameter for the Event Command is encountered
 */
public class MissingEventParamException extends DukeException {
    /**
     * Returns a String representation of the exception
     * @return A String representation of the exception
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! Remember to use \"/at\" for Events.";
    }
}
