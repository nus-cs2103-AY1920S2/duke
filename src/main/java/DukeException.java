/**
 * An exception that is thrown when Duke encounters an error.
 */
public class DukeException extends Exception {
    public DukeException() {}
    public DukeException(String msg) {
        super(msg);
    }
}
