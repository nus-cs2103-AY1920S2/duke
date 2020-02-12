package duke.exception;

/**
 * Indicates an exception when the start time of an event is after the end time.
 */
public class InvalidEventTimeException extends DukeException {
    /**
     * Constructs an InvalidEventTimeException.
     */
    public InvalidEventTimeException() {
        super("Start time cannot be after end time.");
    }
}
