package exception;

/**
 * Exception class for Duke
 */
public abstract class DukeException extends Exception {
    String exception;
    public DukeException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return exception;
    }
}
