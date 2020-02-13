package exception;

/**
 * Represents an exception when a description is missing in the command.
 */
public class MissingDescriptionException extends Exception {
    public MissingDescriptionException(String message) {
        super(message);
    }
}