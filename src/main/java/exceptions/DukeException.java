package exceptions;

/**
 * Handles the exception when running the Duke class.
 */

public class DukeException extends Exception {
    public DukeException() {
        System.out.println(super.getMessage());
    }

    public DukeException(String message) {
        super(message);
    }
}
