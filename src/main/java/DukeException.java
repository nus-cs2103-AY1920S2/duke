/**
 * Represents a duke exception for undefined user behaviour. A <code>DukeException</code> object
 * corresponds to an exception represented by the detail e.g.,
 * <code>"deadline"</code>
 */
public class DukeException extends Exception {

    public String detail;

    /**
     * Constructor for a duke exception.
     * @param detail detail of the exception
     */
    public DukeException(String detail) {
        super(detail);

        this.detail = detail;
    }

    @Override
    public String toString() {
        if (!detail.equals("")) {
            return "--------------------------------------------------\n" +
                    "Why you so dumb? The description of " + detail + " cannot be empty! SMH \n" +
                    "--------------------------------------------------\n";
        } else {
            return "--------------------------------------------------\n" +
                    "Nani? What talking you? \n" +
                    "--------------------------------------------------\n";
        }
    }
}
