package exception;

/**
 * CS2103 Individual Project.
 * DukeException represents all the potential exceptions.
 * @author Wei Cheng
 */
public class DukeException extends IllegalArgumentException {
    /**
     * Constructor.
     * @param message Error message
     */

    public DukeException(String message) {
        super(message);
    }
    /**
     * String representation of the error.
     * @return String
     */

    @Override
    public String toString() {
        return getMessage();
    }
}
