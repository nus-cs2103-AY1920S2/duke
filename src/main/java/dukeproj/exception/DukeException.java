package dukeproj.exception;

/**
 * Represents an exception that is thrown and handled by the DukeProject.
 * Serves as parent class to all exceptions in dukeproj.Duke.
 */
public class DukeException extends Exception {
    public DukeException(String errorMsg) {
        super(errorMsg);
    }
}
