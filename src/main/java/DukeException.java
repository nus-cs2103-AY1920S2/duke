/**
 * General exception class for Duke classes.
 * The error message will be directly used with e.getMessage() to provide error messages to the user.
 */
public class DukeException extends RuntimeException {
    /**
     * Constructs a new DukeException instance.
     * @param message User-friendly error message
     */
    DukeException(String message) {
        super(message);
    }
}
