package duke.exception;

/**
 * Exception to throw when deadline and event does not specify a time.
 */
public class MissingTimeException extends DukeException {
    public MissingTimeException(String type) {
        super(type + " is missing time!");
    }
}
