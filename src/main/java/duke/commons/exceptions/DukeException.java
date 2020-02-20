package duke.commons.exceptions;

/**
 * Represents an exception specific to the Duke application.
 */

public class DukeException extends Exception{
    public DukeException(String message) {
        super(message);
    }
}