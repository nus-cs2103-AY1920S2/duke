package duke.exception;

import java.lang.Exception;

/**
 * Represents the base exception class for all Duke related exceptions.
 */
public class DukeException extends Exception {
    /**
     * Constructs a fresh instance of a Duke exception.
     * @param message The exception message.
     */
    public DukeException(String message) {
        super(message);
    }
}