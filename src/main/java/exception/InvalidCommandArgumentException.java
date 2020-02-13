package exception;

/**
 * Represents the exception when unsupported commands are entered.
 */
public class InvalidCommandArgumentException extends Exception {
    public InvalidCommandArgumentException(String message) {
        super(message);
    }
}