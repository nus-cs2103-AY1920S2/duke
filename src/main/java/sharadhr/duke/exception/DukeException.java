package sharadhr.duke.exception;

/**
 * An exception thrown to denote that an expected, but invalid program state has
 * been reached. These exceptions are straightforward, and should be caught and
 * handled.
 */
public class DukeException extends Exception {
    private static final long serialVersionUID = 7354097854189773198L;
    protected String thrownBy;

    public DukeException(String message, String thrownBy) {
        super(message);
        this.thrownBy = thrownBy;
    }

    @Override public String toString() {
        return String.format("Message: %s%nStack trace: %s", this.getMessage(), getStackTrace());
    }
}