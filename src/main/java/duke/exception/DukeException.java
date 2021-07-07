package duke.exception;

/**
 * The Class DukeException extends Exception class.
 */
public class DukeException extends Exception {

    /**
     * The error msg.
     */
    String errorMsg;

    /**
     * Instantiates a new duke exception.
     *
     * @param s the s
     */
    public DukeException(String s) {
        super(s);
        this.errorMsg = s;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return this.errorMsg;
    }
}
