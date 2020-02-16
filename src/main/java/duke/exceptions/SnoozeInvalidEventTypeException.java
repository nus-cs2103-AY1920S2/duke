package duke.exceptions;

/**
 * Custom Exception to be used when there is an attempt to Snooze a Task which does not support Snoozing
 */
public class SnoozeInvalidEventTypeException extends DukeException {
    /**
     * Returns a String representation of the exception
     * @return A String representation of the exception
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! This task does not support snoozing. Please check your inputs and try again!";
    }
}
