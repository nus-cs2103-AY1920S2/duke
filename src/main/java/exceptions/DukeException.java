package exceptions;

/**
 * Duke's Exception that will be thrown when errors related to Duke's functionality are encountered.
 */
public class DukeException extends Exception {
    public DukeException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
