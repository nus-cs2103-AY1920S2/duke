package sharadhr.duke.exception;

/**
 * DukeInvalidDateException
 */
public class DukeInvalidDateTimeException extends DukeException {
    private static final long serialVersionUID = 1967070878565904626L;

    public DukeInvalidDateTimeException(String thrownBy, String dateString) {
        super("Invalid date format: " + dateString, thrownBy);
    }

    @Override public String toString() {
        return String.format("%s%nThrown by: %s", this.getMessage(), this.thrownBy);
    }
}