package duke;

/**
 * Represents any error that occurs in the context of Duke (excluding IO application).
 */
public class DukeException extends Exception {
    /**
     * Constructs a new Duke Exception object.
     *
     * @param s The message of the error.
     */
    public DukeException(String s) {
        // Call constructor of parent Exception
        super(s);
    }

    @Override
    public String toString() {
        return "OOPS!!! " + getMessage();
    }
}
