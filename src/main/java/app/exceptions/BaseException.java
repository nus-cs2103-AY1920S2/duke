package app.exceptions;

/**
 * This class is the base class for all custom exceptions
 * related to Duke.
 */
public class BaseException extends Exception {
    /**
     * Initialises a new BaseException object with a
     * custom error message.
     * @param message The error message
     */
    public BaseException(String message) {
        super(message);
    }
}