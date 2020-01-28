package jiachen.duke;

/**
 * General Duke exception for more specific exceptions to implement
 */
public abstract class DukeException extends Exception {
    /**
     * Instantiates a new Duke exception.
     *
     * @param errMsg the err msg
     */
    public DukeException(String errMsg) {
        super(errMsg);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! " + this.getMessage();
    }
}
