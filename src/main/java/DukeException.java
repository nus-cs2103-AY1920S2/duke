public class DukeException extends Exception {
    public DukeException() {
    }
    public DukeException(String message) {
        super(message);
    }
    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }
    public DukeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public DukeException(Throwable cause) {
        super(cause);
    }
}