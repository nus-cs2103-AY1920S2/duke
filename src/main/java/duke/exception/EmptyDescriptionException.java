package duke.exception;

import java.lang.Exception;

/**
 * Represents the exception thrown when the user input contains an empty description for tasks.
 */
public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}