package exception;

/**
 * Represents an exception when there is missing date or time in commands.
 */
public class MissingDateTimeException extends Exception {
    public MissingDateTimeException(String message) {
        super(message);
    }
}