package duke.other;

/**
 * Represents an Exception, specific to Duke. A DukeException object corresponds to an exception represented by a String
 * error message.
 */
public class DukeException extends ArrayIndexOutOfBoundsException {
    private String message;
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
